package dhbw.engine;

import dhbw.engine.impl.input.InputListeners;

public interface JavaEngine {
	/**
	 * Open the window
	 */
	void showWindow();

	/**
	 * only temporarily here
	 */
	void update();

	/**
	 * only temporarily here
	 */
	boolean isClosed();

	/**
	 * used to add key/mouse listeners
	 *
	 * @return
	 */
	InputListeners getInputs();
}
