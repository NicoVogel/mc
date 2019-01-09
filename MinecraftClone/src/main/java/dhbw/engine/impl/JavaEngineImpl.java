package dhbw.engine.impl;

import dhbw.engine.JavaEngine;
import dhbw.engine.impl.display.DisplayManager;

public class JavaEngineImpl implements JavaEngine {

	private DisplayManager displayManager;

	public JavaEngineImpl() {
		this.displayManager = new DisplayManager();
	}

	public void showWindow() {
		this.displayManager.create();
	}

}
