import dhbw.engine.EngineProvider;
import dhbw.engine.JavaEngine;
import dhbw.engine.impl.input.KeyInputListener;
import dhbw.engine.impl.input.MouseInputListener;

public class TestRun {

	public static void main(String[] args) {
		JavaEngine engine = EngineProvider.getEngine();
		KeyInputListener key = e -> {
			if (e.isKeyPressed())
				System.out.println("hallo " + e);
		};
		MouseInputListener mouse = e -> {
			if (e.isMousePressed())
				System.out
						.println(String.format("mouse click at x: %.2f; y: %.2f", e.getMousePosX(), e.getMousePosY()));
		};
		engine.getInputs().addKeyListener(key);
		engine.getInputs().addMouseListener(mouse);
		engine.showWindow();
		while (!engine.isClosed()) {
			engine.update();
		}
	}

}
