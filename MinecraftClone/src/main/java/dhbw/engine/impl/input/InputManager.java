package dhbw.engine.impl.input;

import org.lwjgl.glfw.GLFW;

import dhbw.engine.EventProvider;
import dhbw.engine.impl.display.DisplayManager;

public class InputManager implements InputListeners {

	private DisplayManager displayManager;
	private boolean keyDown[];
	private boolean mouseDown[];

	private EventProvider<KeyInputListener, KeyEvent> keyEventProvider;
	private EventProvider<MouseInputListener, MouseEvent> mouseEventProvider;

	public InputManager(DisplayManager displayManager) {
		this.displayManager = displayManager;
		if (this.displayManager == null) {
			throw new IllegalArgumentException("the display manager was not set to an instance");
		}
		this.keyDown = new boolean[GLFW.GLFW_KEY_LAST];
		this.mouseDown = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
		this.keyEventProvider = new EventProvider<>();
		this.mouseEventProvider = new EventProvider<>();
	}

	public void update() {
		for (int i = 0; i < this.keyDown.length; i++) {
			boolean current = this.displayManager.isKeyDown(i);
			boolean pressed = !this.keyDown[i] && current;
			boolean released = this.keyDown[i] && !current;
			this.keyDown[i] = current;
			if (current || pressed || released) {
				this.keyEventProvider.invoke(new KeyEvent(this, i, current, pressed, released));
			}
		}
		for (int i = 0; i < this.mouseDown.length; i++) {
			boolean current = this.displayManager.isMouseDown(i);
			boolean pressed = !this.mouseDown[i] && current;
			boolean released = this.mouseDown[i] && !current;
			this.mouseDown[i] = current;
			if (current || pressed || released) {
				double x = this.displayManager.getMouseX();
				double y = this.displayManager.getMouseY();
				this.mouseEventProvider.invoke(new MouseEvent(this, i, current, pressed, released, x, y));
			}
		}
	}

	@Override
	public void addKeyListener(KeyInputListener keyListener) {
		this.keyEventProvider.add(keyListener);
	}

	@Override
	public void remove(KeyInputListener keyListener) {
		this.keyEventProvider.remove(keyListener);
	}

	@Override
	public void addMouseListener(MouseInputListener mouseListener) {
		this.mouseEventProvider.add(mouseListener);
	}

	@Override
	public void remove(MouseInputListener mouseListener) {
		this.mouseEventProvider.remove(mouseListener);
	}

}
