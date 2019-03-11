package mc.core.event.list;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListAddEvent<E> {

	private E oldObject;
	private E newObject;

}
