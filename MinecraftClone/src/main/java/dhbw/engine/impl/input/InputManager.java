package dhbw.engine.impl.input;

/**
 * subscribe to key and mouse actions done in the window
 * 
 * @author Nico
 *
 */
public interface InputManager {

	void add(KeyInputListener keyListener);

	void remove(KeyInputListener keyListener);

	void add(MouseInputListener mouseListener);

	void remove(MouseInputListener mouseListener);
}
