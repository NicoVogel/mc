package dhbw.engine.impl.display.shaders;

public abstract class Shader {

	private int vertexShaderID;
	private int fragmentShaderID;
	private int programID;
	private String vertexFile;
	private String fragmentFile;

	public Shader(String vertexFile, String fragmentFile) {
		this.vertexFile = vertexFile;
		this.fragmentFile = fragmentFile;
	}

	public void create() {

	}

	public void bind() {

	}

	public void remove() {

	}
}
