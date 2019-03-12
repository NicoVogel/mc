package mc.core.event;

import java.util.HashSet;

public class Event<T> implements EventProvider<T>, Disposable {

	private HashSet<EventListener<T>> listeners;

	private HashSet<EventListener<T>> getListeners() {
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
	public void add(EventListener<T> listener) {
		getListeners().add(listener);
	}

	/**
	 * remove an listener
	 * 
	 * @param listener
	 */
	public void remove(EventListener<T> listener) {
		getListeners().remove(listener);
	}

	/**
	 * fire event
	 * 
	 * @param object
	 */
	public void invoke(Object sender, T object) {
		for (EventListener<T> eventInvoke : listeners) {
			eventInvoke.listen(sender, object);
		}
	}

	@Override
	public void dispose() {
		this.listeners.clear();
	}
}
