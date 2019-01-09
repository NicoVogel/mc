package dhbw.engine;

import dhbw.engine.impl.JavaEngineImpl;

/**
 * this provider is supposed to be used in the code, because the engine can be
 * swap by inheriting from this class by using the setJavaEngin function
 * 
 * @author Nico
 *
 */
public class EngineProvider {
	protected EngineProvider() {

	}

	private static JavaEngine engine;

	/**
	 * get the engine
	 * 
	 * @return
	 */
	public static JavaEngine getEngine() {
		if (EngineProvider.engine == null) {
			EngineProvider.engine = new JavaEngineImpl();
		}
		return EngineProvider.engine;
	}

	/**
	 * used to swap the engine
	 * 
	 * @param engine
	 */
	protected void setJavaEngine(JavaEngine engine) {
		EngineProvider.engine = engine;
	}
}
