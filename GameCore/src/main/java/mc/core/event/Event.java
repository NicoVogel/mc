package mc.core.event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Event<T> {

	private EventProvider<T> provider;

	public void invoke(T object) {
		this.provider.invoke(object);
	}
}
