package mc.core.engine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WindowCloseEvent {

	private boolean engineForceClose;

}
