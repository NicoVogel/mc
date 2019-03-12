package mc.core.engine;

import java.util.Queue;

import mc.core.engine.model.InputEvent;
import mc.core.event.EventProvider;
import mc.core.world.Player;
import mc.core.world.PlayerView;
import mc.core.world.event.ChunkEvent;

public interface Engine {

	Queue<InputEvent> getInputQueue();

	Queue<ChunkEvent> getChunkQueue();

	Player getPlayer();

	PlayerView getView();

	void setPlayer(Player player);

	void setView(PlayerView view);

	void start();

	String getDescription();

	EventProvider<WindowCloseEvent> OnClosingWindow();

	void close();
}
