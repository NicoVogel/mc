package mc.engine;

import java.util.LinkedList;
import java.util.Queue;

import lombok.Getter;
import lombok.Setter;
import mc.core.engine.Engine;
import mc.core.engine.model.InputEvent;
import mc.core.world.ChunkEvent;
import mc.core.world.Player;
import mc.core.world.PlayerView;

@Getter
public class OpenGLCppEngine implements Engine {

	private Queue<InputEvent> input;
	private Queue<ChunkEvent> chunk;
	@Setter
	private Player player;
	@Setter
	private PlayerView view;
	
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

}
