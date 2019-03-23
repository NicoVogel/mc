package mc.core.engine;

import java.util.Queue;

import mc.core.engine.model.InputEvent;
import mc.core.event.EventProvider;
import mc.core.world.Player;
import mc.core.world.PlayerView;
import mc.core.world.event.PlayerViewEvent;

public interface Engine {

	/**
	 * The engine queues the user inputs here
	 * 
	 * @return
	 */
	Queue<InputEvent> getInputQueue();

	/**
	 * The core logic queues updates which are in reach of the user here
	 * 
	 * @return
	 */
	Queue<PlayerViewEvent> getChunkQueue();

	/**
	 * The current player model
	 * 
	 * @return
	 */
	Player getPlayer();

	/**
	 * The current view model
	 * 
	 * @return
	 */
	PlayerView getView();

	/**
	 * set a player. if the player is null, an Exception is thrown
	 * 
	 * @param player
	 * 
	 * @exception IllegalArgumentException
	 */
	void setPlayer(Player player);

	/**
	 * set the view of the player.if the player is null, an Exception is thrown
	 * 
	 * @param view
	 * 
	 * @exception IllegalArgumentException
	 */
	void setView(PlayerView view);

	/**
	 * start the engine
	 */
	void start();

	/**
	 * get the engine description
	 * 
	 * @return
	 */
	String getDescription();

	/**
	 * is invoked when the window is closing
	 * 
	 * @return
	 */
	EventProvider<WindowCloseEvent> OnClosingWindow();

	/**
	 * close the window, will also trigger the event
	 */
	void close();
}
