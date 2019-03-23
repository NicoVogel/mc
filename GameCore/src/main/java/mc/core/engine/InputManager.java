package mc.core.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;

import mc.core.engine.model.InputEvent;
import mc.core.engine.model.KeyEvent;
import mc.core.engine.model.MouseEvent;
import mc.core.event.Disposable;
import mc.core.event.Disposer;
import mc.core.event.Event;
import mc.core.event.EventProvider;
import mc.core.update.Updateable;

public class InputManager implements Updateable, Disposable {

	private Engine engine;

	private Event<KeyEvent> keyEvent;
	private Event<MouseEvent> mouseEvent;
	private Disposer disposer = new Disposer(this.keyEvent, this.mouseEvent);

	public InputManager(Engine engine) {
		this.engine = engine;
		if (this.engine == null) {
			throw new IllegalArgumentException("cannot create an input manager without a engine");
		}
		this.keyEvent = new Event<>();
		this.mouseEvent = new Event<>();
	}

	@Override
	public void update() {
		Queue<InputEvent> inputQueue = this.engine.getInputQueue();
		HashSet<Character> keyInput = new HashSet<>();
		HashSet<Integer> mouseInput = new HashSet<>();
		List<KeyEvent> keyEvent = new ArrayList<>();
		List<MouseEvent> mouseEvent = new ArrayList<>();

		// for all events in queue
		while (inputQueue.isEmpty() == false) {
			InputEvent event = inputQueue.poll();
			if (event.isKeyEvent()) {

				// get input as key event and add it if it does not already exist
				KeyEvent key = event.newKeyEvent(this.engine);
				if (keyInput.add(key.getKey())) {
					keyEvent.add(key);
				}
			} else {

				// get input as mouse event and add if it does not already exist
				MouseEvent mouse = event.newMouseEvent(this.engine);
				if (mouseInput.add(mouse.getMouseCode())) {
					mouseEvent.add(mouse);
				}
			}
		}

		// invoke all event listener
		for (MouseEvent m : mouseEvent) {
			this.mouseEvent.invoke(this.engine, m);
		}
		for (KeyEvent k : keyEvent) {
			this.keyEvent.invoke(this.engine, k);
		}
	}

	public EventProvider<KeyEvent> OnKey() {
		return this.keyEvent;
	}

	public EventProvider<MouseEvent> OnMouse() {
		return this.mouseEvent;
	}

	@Override
	public void dispose() {
		this.disposer.dispose();
	}
}
