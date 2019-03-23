package mc.engine;

import java.util.LinkedList;
import java.util.Queue;

import org.joml.Vector2d;
import org.joml.Vector3d;

import lombok.Getter;
import lombok.NoArgsConstructor;
import mc.core.engine.Engine;
import mc.core.engine.model.InputEvent;
import mc.core.engine.model.WindowCloseEvent;
import mc.core.event.Event;
import mc.core.event.EventListener;
import mc.core.event.EventProvider;
import mc.core.event.OldNewValueEvent;
import mc.core.world.Player;
import mc.core.world.PlayerView;
import mc.core.world.event.PlayerViewEvent;

@Getter
@NoArgsConstructor
public class OpenGLCppEngine implements Engine {

	private Queue<InputEvent> input;
	private Queue<PlayerViewEvent> update;
	private PlayerPositionListener positionListener = new PlayerPositionListener();
	private PlayerCameraListener cameraListener = new PlayerCameraListener();
	private Player player;
	private Event<WindowCloseEvent> onClose;
	private PlayerView view;

	static {
//TODO native methods einbinden
	}

	@Override
	public void setView(PlayerView view) {
		if (view == null) {
			throw new IllegalArgumentException("cannot set a playerview which is null!");
		}
		this.view = view;
	}

	@Override
	public void setPlayer(Player player) {
		if (player == null) {
			throw new IllegalArgumentException("cannot set a player which is null!");
		}
		if (this.player != null) {
			this.player.OnPositionUpdate().remove(this.positionListener);
			this.player.OnCameraUpdate().remove(this.cameraListener);
		}
		this.player = player;
		this.player.OnPositionUpdate().add(this.positionListener);
		this.player.OnCameraUpdate().add(this.cameraListener);
	}

	@Override
	public Queue<InputEvent> getInputQueue() {
		if (this.input == null) {
			this.input = new ObservableLinkedList<>();
		}
		return this.input;
	}

	@Override
	public Queue<PlayerViewEvent> getChunkQueue() {
		if (this.update == null) {
			this.update = new LinkedList<>();
		}
		return this.update;
	}

	@Override
	public void start() {
		if (this.player == null) {
			throw new IllegalArgumentException("Cannot start the Engine without a player");
		}
		if (this.view == null) {
			throw new IllegalArgumentException("Cannot start the Engine without a view");
		}

		updatePlayerPosition(this.player.getX(), this.player.getY(), this.player.getZ());
		updatePlayerCamera(this.player.getPitch(), this.player.getYaw());
		run();
	}

	@Override
	public String getDescription() {
		return "OpenGL 3 C++ Implementation";
	}

	@Override
	public EventProvider<WindowCloseEvent> OnClosingWindow() {
		return this.onClose;
	}

	@Override
	public void close() {
		stop();
		this.onClose.invoke(this, new WindowCloseEvent(false));
	}

	/**
	 * informs c++ about and world update which is in player range
	 */
	public void incommingUpdate() {
		PlayerViewEvent e = getUpdate().poll();
		if (e != null) {
			updateWorld(e);
		}
	}

	/**
	 * update player position in c++
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public native void updatePlayerPosition(double x, double y, double z);

	/**
	 * update player camera in c++
	 * 
	 * @param pitch
	 * @param yaw
	 */
	public native void updatePlayerCamera(double pitch, double yaw);

	/**
	 * start c++ rendering
	 */
	public native void run();

	/**
	 * stop the c++ rendering
	 */
	public native void stop();

	/**
	 * is called if an update in incoming for the world and informs c++ about it
	 * 
	 * @param update
	 */
	public native void updateWorld(PlayerViewEvent update);

	/**
	 * called by c++ to inform java, that the window was closed/ is closing
	 */
	public void userClosedWindow() {
		this.onClose.invoke(this, new WindowCloseEvent(true));
	}

	/**
	 * called by c++ to inform java, that a mouse event happend
	 * 
	 * @param mouseCode
	 * @param mouseDown
	 * @param mousePressed
	 * @param mouseRelease
	 * @param mousePosX
	 * @param mousePosY
	 * @param pressedSince
	 */
	public void createMouseEvent(int mouseCode, boolean mouseDown, boolean mousePressed, boolean mouseRelease,
			double mousePosX, double mousePosY, int pressedSince) {
		getInputQueue().add(
				new InputEvent(mouseCode, mouseDown, mousePressed, mouseRelease, mousePosX, mousePosY, pressedSince));
	}

	/**
	 * called by c++ to inform java, that a key event happend
	 * 
	 * @param key
	 * @param keyDown
	 * @param keyPressed
	 * @param keyRelease
	 * @param pressedSince
	 */
	public void createKeyEvent(char key, boolean keyDown, boolean keyPressed, boolean keyRelease, int pressedSince) {
		getInputQueue().add(new InputEvent(key, keyDown, keyPressed, keyRelease, pressedSince));
	}

	/**
	 * used to update player position
	 * 
	 * @author Nico
	 *
	 */
	private class PlayerPositionListener implements EventListener<OldNewValueEvent<Vector3d>> {

		@Override
		public void listen(Object sender, OldNewValueEvent<Vector3d> object) {
			updatePlayerPosition(object.getNewValue().x, object.getNewValue().y, object.getNewValue().z);
		}

	}

	/**
	 * used to update camera position
	 * 
	 * @author Nico
	 *
	 */
	private class PlayerCameraListener implements EventListener<OldNewValueEvent<Vector2d>> {

		@Override
		public void listen(Object sender, OldNewValueEvent<Vector2d> object) {
			updatePlayerCamera(object.getNewValue().x, object.getNewValue().y);
		}

	}

	/**
	 * used to invoke the incommingUpdate method if new information is passed into
	 * the queue
	 * 
	 * @author Nico
	 *
	 * @param <T>
	 */
	private class ObservableLinkedList<T> extends LinkedList<T> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1194559243239094557L;

		@Override
		public boolean add(T e) {
			if (super.add(e)) {
				incommingUpdate();
				return true;
			}
			return false;
		}
	}

}
