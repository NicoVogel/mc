package mc.core.engine;

import java.util.Queue;

import mc.core.engine.model.InputEvent;
import mc.core.world.ChunkEvent;
import mc.core.world.Player;
import mc.core.world.PlayerView;

public interface Engine {

	Queue<InputEvent> getEventQueue();

	Queue<ChunkEvent> getChunkQueue();

	Player getPlayer();
	
	PlayerView getView();
	
	void setPlayer(Player player);
	
	void setView(PlayerView view);
	
	void start();

}
