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

	/**
	 * add an listener
	 * 
	 * @param listener
	 */
	public void add(EventInvoke<T> listener) {
		getListeners().add(listener);
	}

	/**
	 * remove an listener
	 * 
	 * @param listener
	 */
	public void remove(EventInvoke<T> listener) {
		getListeners().remove(listener);
	}

	/**
	 * invoke the event
	 * 
	 * @param object
	 */
	public void invoke(T object) {
		for (EventInvoke<T> eventInvoke : listeners) {
			eventInvoke.invoke(object);
		}
	}

}
