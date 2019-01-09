package dhbw.engine.impl;

import dhbw.engine.JavaEngine;

public class JavaEngineImpl implements JavaEngine {

	private JavaEngineImpl() {

	}

	private static JavaEngineImpl engine;

	public static JavaEngine getEngine() {
		return engine;
	}

}
