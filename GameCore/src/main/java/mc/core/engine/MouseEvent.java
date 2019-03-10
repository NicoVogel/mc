package mc.core.engine;

import java.util.EventObject;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MouseEvent extends EventObject {

	private static final long serialVersionUID = -8191581388078557863L;

	private int mouseCode;
	private boolean mouseDown;
	private boolean mousePressed;
	private boolean mouseRelease;

	private double mousePosX;
	private double mousePosY;

	private int pressedSince;

	public MouseEvent(Object source, int mouseCode, boolean mouseDown, boolean mousePressed, boolean mouseRelease,
			double mousePosX, double mousePosY, int pressedSince) {
		super(source);
		this.mouseCode = mouseCode;
		this.mouseDown = mouseDown;
		this.mousePressed = mousePressed;
		this.mouseRelease = mouseRelease;
		this.mousePosX = mousePosX;
		this.mousePosY = mousePosY;
		this.pressedSince = pressedSince;
	}

}
