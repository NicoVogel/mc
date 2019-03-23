package mc.core.engine.model;

import lombok.Getter;
import mc.core.engine.Engine;

@Getter
public class InputEvent {

	// in miliseconds
	private int pressedSince;

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
			double mousePosY, int pressedSince) {
		this.mouseCode = mouseCode;
		this.mouseDown = mouseDown;
		this.mousePressed = mousePressed;
		this.mouseRelease = mouseRelease;
		this.mousePosX = mousePosX;
		this.mousePosY = mousePosY;
	}

	public InputEvent(char key, boolean keyDown, boolean keyPressed, boolean keyRelease, int pressedSince) {
		this.key = key;
		this.keyDown = keyDown;
		this.keyPressed = keyPressed;
		this.keyRelease = keyRelease;
	}

	public boolean isKeyEvent() {
		return this.mouseCode == 0;
	}

	public KeyEvent newKeyEvent(Engine sender) {
		return new KeyEvent(sender, key, keyDown, keyPressed, keyRelease, pressedSince);
	}

	public MouseEvent newMouseEvent(Engine sender) {
		return new MouseEvent(sender, mouseCode, mouseDown, mousePressed, mouseRelease, mousePosX, mousePosY,
				pressedSince);
	}

}
