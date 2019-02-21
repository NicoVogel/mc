package mc.core.event;

import java.util.HashSet;

public class EventProvider<T> {

	private HashSet<EventInvoke<T>> listeners;

	private HashSet<EventInvoke<T>> getListeners() {
		if (this.listeners == null) {
			this.listeners = new HashSet<>();
		}
		return this.listeners;
	}

	public void add(EventInvoke<T> listener) {
		getListeners().add(listener);
	}

	public void remove(EventInvoke<T> listener) {
		getListeners().remove(listener);
	}

	public void invoke(T object) {
		for (EventInvoke<T> eventInvoke : listeners) {
			eventInvoke.invoke(object);
		}
	}

}
