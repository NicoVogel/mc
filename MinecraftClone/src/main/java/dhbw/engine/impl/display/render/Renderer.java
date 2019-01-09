package dhbw.engine.impl.display.render;

import org.lwjgl.opengl.GL11;

public class Renderer {

	public void renderModel(Model model) {
		GLUtils.gl30_bindVertex(model.getVertexArrayID(), () -> {
			GLUtils.gl20_enableVertex(0, () -> {
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			});
		});
	}

}
