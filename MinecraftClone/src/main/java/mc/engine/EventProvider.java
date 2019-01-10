package mc.engine;

import java.util.ArrayList;
import java.util.EventObject;

public class EventProvider<LISTENER extends EventListener<EVENT>, EVENT extends EventObject> {

	private ArrayList<LISTENER> listeners = new ArrayList<>();

	public void add(LISTENER listener) {
		this.listeners.add(listener);
	}

	public void remove(LISTENER listener) {
		this.listeners.remove(listener);
	}

	public void invoke(EVENT e) {
		listeners.stream().forEach(x -> x.invoke(e));
	}
}
