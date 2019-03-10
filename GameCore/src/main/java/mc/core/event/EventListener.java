package mc.core.event;

@FunctionalInterface
public interface EventListener<T> {

	void listen(Object sender, T object);

}
