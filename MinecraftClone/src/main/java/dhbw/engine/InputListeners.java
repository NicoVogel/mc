package dhbw.engine;

import dhbw.engine.impl.input.KeyInputListener;
import dhbw.engine.impl.input.MouseInputListener;

/**
 * subscribe to key and mouse actions done in the window
 * 
 * @author Nico
 *
 */
public interface InputListeners {

	void add(KeyInputListener keyListener);

	void remove(KeyInputListener keyListener);

	void add(MouseInputListener mouseListener);

	void remove(MouseInputListener mouseListener);
}
