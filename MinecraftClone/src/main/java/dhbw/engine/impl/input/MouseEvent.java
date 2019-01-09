package dhbw.engine.impl.input;

import java.util.EventObject;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MouseEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8191581388078557863L;

	private int mouseCode;
	private boolean mouseDown;
	private boolean mousePressed;
	private boolean mouseRelease;

	public MouseEvent(Object source, int mouseCode, boolean mouseDown, boolean mousePressed, boolean mouseRelease) {
		super(source);
		this.mouseCode = mouseCode;
		this.mouseDown = mouseDown;
		this.mousePressed = mousePressed;
		this.mouseRelease = mouseRelease;
	}

}
