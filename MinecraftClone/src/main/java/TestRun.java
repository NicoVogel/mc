import dhbw.engine.EngineProvider;
import dhbw.engine.JavaEngine;

public class TestRun {

	public static void main(String[] args) {
		JavaEngine engine = EngineProvider.getEngine();
		engine.showWindow();
		while (!engine.isClosed()) {
			engine.update();
		}
	}

}
