package mc.engine;

import java.util.LinkedList;
import java.util.Queue;

import org.joml.Vector2d;
import org.joml.Vector3d;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mc.core.engine.Engine;
import mc.core.engine.WindowCloseEvent;
import mc.core.engine.model.InputEvent;
import mc.core.event.Event;
import mc.core.event.EventListener;
import mc.core.event.EventProvider;
import mc.core.event.OldNewValueEvent;
import mc.core.world.Player;
import mc.core.world.PlayerView;
import mc.core.world.event.PlayerViewEvent;
import mc.engine.modelmaps.PlayerTO;

@Getter
@NoArgsConstructor
public class OpenGLCppEngine implements Engine {

	private Queue<InputEvent> input;
	private Queue<PlayerViewEvent> chunk;
	private PlayerPositionListener positionListener = new PlayerPositionListener();
	private PlayerCameraListener cameraListener = new PlayerCameraListener();
	private Player player;
	private PlayerTO playerTo;
	private Event<WindowCloseEvent> onClose;
	@Setter
	private PlayerView view;

	public void setPlayer(Player player) {
		if (player == null) {
			throw new IllegalArgumentException("cannot set a player which is null!");
		}
		if (this.player != null) {
			this.player.OnPositionUpdate().remove(this.positionListener);
			this.player.OnCameraUpdate().remove(this.cameraListener);
		}
		this.player = player;
		this.playerTo = convert(this.player);
		this.player.OnPositionUpdate().add(this.positionListener);
		this.player.OnCameraUpdate().add(this.cameraListener);
	}

	@Override
	public Queue<InputEvent> getInputQueue() {
		if (this.input == null) {
			this.input = new LinkedList<>();
		}
		return this.input;
	}

	@Override
	public Queue<PlayerViewEvent> getChunkQueue() {
		if (this.chunk == null) {
			this.chunk = new LinkedList<>();
		}
		return this.chunk;
	}

	@Override
	public void start() {
		if (this.player == null) {
			throw new IllegalArgumentException("Cannot start the Engine without a player");
		}
		if (this.view == null) {
			throw new IllegalArgumentException("Cannot start the Engine without a view");
		}

		// TODO add code which executes the cpp library
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
		this.onClose.invoke(this, new WindowCloseEvent(true));
	}

	public void createMouseEvent(int mouseCode, boolean mouseDown, boolean mousePressed, boolean mouseRelease,
			double mousePosX, double mousePosY, int pressedSince) {
		getInputQueue().add(
				new InputEvent(mouseCode, mouseDown, mousePressed, mouseRelease, mousePosX, mousePosY, pressedSince));
	}

	public void createKeyEvent(char key, boolean keyDown, boolean keyPressed, boolean keyRelease, int pressedSince) {
		getInputQueue().add(new InputEvent(key, keyDown, keyPressed, keyRelease, pressedSince));
	}

	private PlayerTO convert(Player player) {
		PlayerTO to = new PlayerTO();
		to.pitch = player.getPitch();
		to.x = player.getX();
		to.y = player.getY();
		to.yaw = player.getYaw();
		to.z = player.getZ();
		return to;
	}

	private class PlayerPositionListener implements EventListener<OldNewValueEvent<Vector3d>> {

		@Override
		public void listen(Object sender, OldNewValueEvent<Vector3d> object) {
			playerTo.x = object.getNewValue().x;
			playerTo.y = object.getNewValue().y;
			playerTo.z = object.getNewValue().z;
		}

	}

	private class PlayerCameraListener implements EventListener<OldNewValueEvent<Vector2d>> {

		@Override
		public void listen(Object sender, OldNewValueEvent<Vector2d> object) {
			playerTo.pitch = object.getNewValue().x;
			playerTo.yaw = object.getNewValue().y;
		}

	}

}
