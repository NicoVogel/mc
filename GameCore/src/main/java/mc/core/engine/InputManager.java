package mc.core.engine;

import mc.core.event.Event;

public class InputManager {

	private Engine engine;

	private Event<KeyEvent> keyEventProvider;
	private Event<MouseEvent> mouseEventProvider;

	public InputManager(Engine engine) {
		this.engine = engine;
		if (this.engine == null) {
			throw new IllegalArgumentException("the display manager was not set to an instance");
		}
		this.keyEventProvider = new Event<>();
		this.mouseEventProvider = new Event<>();
	}

	public void update() {
		// TODO check engine buffer
	}

	public EventProvider<KeyEvent> OnKeyEvent
}
