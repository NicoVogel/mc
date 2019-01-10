package mc.engine.impl;

import java.util.List;

import mc.engine.JavaEngine;
import mc.engine.impl.display.DisplayManager;
import mc.engine.impl.display.render.Model;
import mc.engine.impl.frame.FrameCounter;
import mc.engine.impl.input.InputListeners;
import mc.engine.impl.input.InputManager;

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

	@Override
	public List<Model> getModels() {
		return this.displayManager.getModels();
	}

}
