package mc.engine;

import java.util.LinkedList;
import java.util.Queue;

import lombok.Getter;
import lombok.Setter;
import mc.core.engine.Engine;
import mc.core.engine.model.InputEvent;
import mc.core.event.EventListener;
import mc.core.world.Player;
import mc.core.world.PlayerView;
import mc.core.world.event.ChunkEvent;
import mc.core.world.event.PlayerEvent;

@Getter
public class OpenGLCppEngine implements Engine {

	private Queue<InputEvent> input;
	private Queue<ChunkEvent> chunk;
	private Queue<PlayerEvent> position;
	private PlayerPositionListener listener;
	private Player player;
	@Setter
	private PlayerView view;
	
	public OpenGLCppEngine() {
		this.listener = new PlayerPositionListener();
	}
	
	public void setPlayer(Player player) {
		if(player == null) {
			throw new IllegalArgumentException("cannot set a player which is null!");
		}
		if(this.player != null) {
			this.player.OnUpdate().remove(this.listener);
		}
		this.player = player;
		this.player.OnUpdate().add(this.listener);
	}
	
	public Queue<PlayerEvent> getPositionUpdate(){
		if(this.position == null) {
			this.position = new LinkedList<>();
		}
		return this.position;
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
	
	private class PlayerPositionListener implements EventListener<PlayerEvent>{

		@Override
		public void listen(Object sender, PlayerEvent object) {
			position.add(object);
		}
		
	}

}
