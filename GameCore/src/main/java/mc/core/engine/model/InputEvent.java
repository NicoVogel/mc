package mc.core.engine.communication;

import lombok.Getter;

@Getter
public class InputEvent {

	private char key;
	private boolean keyDown;
	private boolean keyPressed;
	private boolean keyRelease;

	private int mouseCode;
	private boolean mouseDown;
	private boolean mousePressed;
	private boolean mouseRelease;

	private double mousePosX;
	private double mousePosY;

	public InputEvent(int mouseCode, boolean mouseDown, boolean mousePressed, boolean mouseRelease, double mousePosX,
			double mousePosY) {
		this.mouseCode = mouseCode;
		this.mouseDown = mouseDown;
		this.mousePressed = mousePressed;
		this.mouseRelease = mouseRelease;
		this.mousePosX = mousePosX;
		this.mousePosY = mousePosY;
	}

	public InputEvent(char key, boolean keyDown, boolean keyPressed, boolean keyRelease) {
		this.key = key;
		this.keyDown = keyDown;
		this.keyPressed = keyPressed;
		this.keyRelease = keyRelease;
	}

	public boolean isKeyEvent() {
		return this.mouseCode == 0;
	}

}
