package mc.core.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OldNewValueEvent<T> {

	private T oldValue;
	private T newValue;
	
}
