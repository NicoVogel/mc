package mc.core.event;

@FunctionalInterface
public interface EventListener<T> {

	void notify(T object);

}
