package mc.engine.impl.display.shaders;

public class BasicShader extends Shader {

	private static final String VERTEX_FILE = "./src/main/resources/basicVertexShader.glsl";
	private static final String FRAGMENT_FILE = "./src/main/resources/basicFragmentShader.glsl";

	public BasicShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	public void bindAllAttributes() {
		super.bindAttribute(0, "vertices");
	}

}
