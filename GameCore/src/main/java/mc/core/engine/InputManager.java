package mc.core.engine;

import mc.core.event.Event;
import mc.core.event.EventProvider;
import mc.core.update.Updateable;

public class InputManager implements Updateable{

	private Engine engine;

	private Event<KeyEvent> keyEvent;
	private Event<MouseEvent> mouseEvent;

	public InputManager(Engine engine) {
		this.engine = engine;
		if (this.engine == null) {
			throw new IllegalArgumentException("the display manager was not set to an instance");
		}
		this.keyEvent = new Event<>();
		this.mouseEvent = new Event<>();
	}

	@Override
	public void update() {
		// TODO check engine buffer
	}

	public EventProvider<KeyEvent> OnKey() {
		return this.keyEvent;
	}

	public EventProvider<MouseEvent> OnMouse() {
		return this.mouseEvent;
	}
}
