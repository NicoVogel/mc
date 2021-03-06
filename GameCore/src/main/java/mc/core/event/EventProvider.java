package mc.core.event;

public interface EventProvider<T> {

	/**
	 * add an listener
	 * 
	 * @param listener
	 */
	void add(EventListener<T> listener);

	/**
	 * remove an listener
	 * 
	 * @param listener
	 */
	void remove(EventListener<T> listener);
}
