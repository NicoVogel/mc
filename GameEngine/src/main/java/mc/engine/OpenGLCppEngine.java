package mc.engine;

import java.util.LinkedList;
import java.util.Queue;

import org.joml.Vector2d;
import org.joml.Vector3d;

import lombok.Getter;
import lombok.Setter;
import mc.core.engine.Engine;
import mc.core.engine.model.InputEvent;
import mc.core.event.EventListener;
import mc.core.event.OldNewEvent;
import mc.core.world.Player;
import mc.core.world.PlayerView;
import mc.core.world.event.ChunkEvent;

@Getter
public class OpenGLCppEngine implements Engine {

	private Queue<InputEvent> input;
	private Queue<ChunkEvent> chunk;
	private Queue<OldNewEvent<Vector3d>> position;
	private Queue<OldNewEvent<Vector2d>> camera;
	private PlayerPositionListener positionListener= new PlayerPositionListener();
	private PlayerCameraListener cameraListener= new PlayerCameraListener();
	private Player player;
	@Setter
	private PlayerView view;
	
	public void setPlayer(Player player) {
		if(player == null) {
			throw new IllegalArgumentException("cannot set a player which is null!");
		}
		if(this.player != null) {
			this.player.OnPositionUpdate().remove(this.positionListener);
			this.player.OnCameraUpdate().remove(this.cameraListener);
		}
		this.player = player;
		this.player.OnPositionUpdate().add(this.positionListener);
		this.player.OnCameraUpdate().add(this.cameraListener);
	}

	public Queue<OldNewEvent<Vector3d>> getPositionUpdate(){
		if(this.position == null) {
			this.position = new LinkedList<>();
		}
		return this.position;
	}
	public Queue<OldNewEvent<Vector2d>> getCameraUpdate(){
		if(this.camera == null) {
			this.camera = new LinkedList<>();
		}
		return this.camera;
	}
	
	@Override
	public Queue<InputEvent> getInputQueue() {
		if(this.input == null) {
			this.input = new LinkedList<>();
		}
		return this.input;
	}

	@Override
	public Queue<ChunkEvent> getChunkQueue() {
		if(this.chunk == null) {
			this.chunk = new LinkedList<>();
		}
		return this.chunk;
	}

	@Override
	public void start() {
		if(this.player == null) {
			throw new IllegalArgumentException("Cannot start the Engine without a player");
		}
		if(this.view == null) {
			throw new IllegalArgumentException("Cannot start the Engine without a view");
		}
		
		//TODO add code which executes the cpp library
	}

	@Override
	public String getDescription() {
		return "OpenGL 3 C++ Implementation";
	}
	
	private class PlayerPositionListener implements EventListener<OldNewEvent<Vector3d>>{

		@Override
		public void listen(Object sender, OldNewEvent<Vector3d> object) {
			position.add(object);
		}
		
	}
	
	private class PlayerCameraListener implements EventListener<OldNewEvent<Vector2d>>{

		@Override
		public void listen(Object sender, OldNewEvent<Vector2d> object) {
			camera.add(object);
		}
		
	}

}
