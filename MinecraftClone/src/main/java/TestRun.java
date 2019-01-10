import dhbw.engine.EngineProvider;
import dhbw.engine.JavaEngine;
import dhbw.engine.impl.display.render.Model;
import dhbw.engine.impl.display.shaders.BasicShader;
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

		BasicShader shader = new BasicShader();
		Model model = new Model(new float[][] { { -0.5f, 0.5f, 0 }, // TOP LEFT 0
				{ 0.5f, 0.5f, 0 }, // TOP RIGHT 1
				{ -0.5f, -0.5f, 0 }, // BOTTOM LEFT 3
				{ 0.5f, -0.5f, 0 } // BOTTOM RIGHT 2
		}, new int[] { 0, 1, 2, 2, 3, 1 });
		engine.getModels().add(model);

		engine.showWindow();
		model.create();
		shader.create();

		while (!engine.isClosed()) {
			engine.update();
			shader.bind();
		}
		shader.remove();
		model.remove();
	}

}
