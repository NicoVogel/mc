package mc.core.event.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OldNewValueEvent<T> {

	private T oldValue;
	private T newValue;

}
