package mc.core.engine;

import java.util.EventObject;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KeyEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3648514306851405007L;

	private char key;
	private boolean keyDown;
	private boolean keyPressed;
	private boolean keyRelease;
	private int pressedSince;

	public KeyEvent(Object source, char key, boolean keyDown, boolean keyPressed, boolean keyRelease,
			int pressedSince) {
		super(source);
		this.key = key;
		this.keyDown = keyDown;
		this.keyPressed = keyPressed;
		this.keyRelease = keyRelease;
		this.pressedSince = pressedSince;
	}

}
