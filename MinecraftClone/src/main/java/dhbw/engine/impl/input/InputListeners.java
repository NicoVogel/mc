package dhbw.engine.impl.input;

/**
 * subscribe to key and mouse actions done in the window
 * 
 * @author Nico
 *
 */
public interface InputListeners {

	void addKeyListener(KeyInputListener keyListener);

	void remove(KeyInputListener keyListener);

	void addMouseListener(MouseInputListener mouseListener);

	void remove(MouseInputListener mouseListener);
}
