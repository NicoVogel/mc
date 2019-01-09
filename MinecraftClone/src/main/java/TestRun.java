import dhbw.engine.EngineProvider;
import dhbw.engine.JavaEngine;

public class TestRun {

	public static void main(String[] args) {
		JavaEngine engine = EngineProvider.getEngine();
		engine.getInputs().addKeyListener(e -> System.out.println("hallo " + e));
		engine.showWindow();
		while (!engine.isClosed()) {
			engine.update();
		}
	}

}
