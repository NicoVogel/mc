package mc.core.event;

public interface EventProvider<T> {

	/**
	 * add an listener
	 * 
	 * @param listener
	 */
	public void add(EventListener<T> listener);

	/**
	 * remove an listener
	 * 
	 * @param listener
	 */
	public void remove(EventListener<T> listener);
}
