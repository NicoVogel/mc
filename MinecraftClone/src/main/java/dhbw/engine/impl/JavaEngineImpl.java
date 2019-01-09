package dhbw.engine.impl;

import dhbw.engine.JavaEngine;
import dhbw.engine.impl.display.DisplayManager;
import dhbw.engine.impl.frame.FrameCounter;
import dhbw.engine.impl.input.InputListeners;
import dhbw.engine.impl.input.InputManager;

public class JavaEngineImpl implements JavaEngine {

	private DisplayManager displayManager;
	private InputManager inputManager;
	private FrameCounter frameCounter;

	public JavaEngineImpl() {
		this.displayManager = new DisplayManager();
		this.inputManager = new InputManager(this.displayManager);
	}

	@Override
	public void showWindow() {
		this.displayManager.create();
		this.frameCounter = new FrameCounter(this.displayManager);
		this.frameCounter.add(e -> System.out.println(e));
	}

	@Override
	public void update() {
		this.frameCounter.update();
		this.displayManager.update();
		this.inputManager.update();
	}

	@Override
	public boolean isClosed() {
		return this.displayManager.isWindowClosed();
	}

	@Override
	public InputListeners getInputs() {
		return this.inputManager;
	}

}
