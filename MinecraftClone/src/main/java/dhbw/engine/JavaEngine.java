package dhbw.engine;

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

	InputListeners getInputs();
}
