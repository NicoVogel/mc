package mc.core.event.interfaces;

@FunctionalInterface
public interface EventListener<T> {

	void listen(Object sender, T object);

}
