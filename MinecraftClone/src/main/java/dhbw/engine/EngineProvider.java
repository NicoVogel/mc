package dhbw.engine;

import dhbw.engine.impl.JavaEngineImpl;

public class EngineProvider {
	protected EngineProvider() {

	}

	private static JavaEngine engine;

	public static JavaEngine getEngine() {
		if (EngineProvider.engine == null) {
			EngineProvider.engine = new JavaEngineImpl();
		}
		return EngineProvider.engine;
	}

	protected void setJavaEngine(JavaEngine engine) {
		EngineProvider.engine = engine;
	}
}
