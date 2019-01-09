package dhbw.engine;

import java.util.List;

import dhbw.engine.impl.display.render.Model;
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
	 * only temporarily here
	 */
	List<Model> getModels();

	/**
	 * used to add key/mouse listeners
	 *
	 * @return
	 */
	InputListeners getInputs();
}
