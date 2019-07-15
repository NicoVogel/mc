package mc.core.event.interfaces;

@FunctionalInterface
public interface Disposable {

	/**
	 * remove all Event listeners, so that this object can can be destroyed by the garbage collector
	 */
	void dispose();
	
}
