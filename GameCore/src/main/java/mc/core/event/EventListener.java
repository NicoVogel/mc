package mc.core.event;

@FunctionalInterface
public interface EventListener<T> {

	void invoke(T object);

}
