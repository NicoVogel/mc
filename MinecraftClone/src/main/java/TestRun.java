import dhbw.engine.EngineProvider;
import dhbw.engine.JavaEngine;
import dhbw.engine.impl.input.KeyInputListener;

public class TestRun {

	public static void main(String[] args) {
		JavaEngine engine = EngineProvider.getEngine();
		KeyInputListener key = e -> {
			if (e.isKeyPressed())
				System.out.println("hallo " + e);
		};
		engine.getInputs().addKeyListener(key);
		engine.showWindow();
		while (!engine.isClosed()) {
			engine.update();
		}
	}

}
