package mc.core.event;

@FunctionalInterface
public interface EventInvoke<T> {

	void invoke(T object);

}
