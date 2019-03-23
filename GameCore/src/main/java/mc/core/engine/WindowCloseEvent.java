package mc.core.engine;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WindowCloseEvent {

	private boolean engineForceClose;

}
