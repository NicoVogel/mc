package dhbw.engine.impl.input;

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

	private int keyCode;
	private boolean keyDown;
	private boolean keyPressed;
	private boolean keyRelease;

	public KeyEvent(Object arg0, int keyCode, boolean keyDown, boolean keyPressed, boolean keyRelease) {
		super(arg0);
		this.keyCode = keyCode;
		this.keyDown = keyDown;
		this.keyPressed = keyPressed;
		this.keyRelease = keyRelease;
	}

}
