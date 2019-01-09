package dhbw.engine.impl.display.shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import dhbw.engine.io.FileReaderWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		this.programID = GL20.glCreateProgram();
		this.vertexShaderID = createSourceCompileCheckShader(this.vertexFile, GL20.GL_VERTEX_SHADER, "vertex");
		this.fragmentShaderID = createSourceCompileCheckShader(this.fragmentFile, GL20.GL_FRAGMENT_SHADER, "fragment");

		GL20.glAttachShader(this.programID, this.vertexShaderID);
		GL20.glAttachShader(this.programID, this.fragmentShaderID);

		GL20.glLinkProgram(this.programID);
		checkForProgrammError(this.programID, GL20.GL_LINK_STATUS, "link");

		GL20.glValidateProgram(this.programID);
		checkForProgrammError(this.programID, GL20.GL_VALIDATE_STATUS, "validate");

	}

	public void bind() {
		GL20.glUseProgram(this.programID);
	}

	public void remove() {
		GL20.glDetachShader(this.programID, this.vertexShaderID);
		GL20.glDetachShader(this.programID, this.fragmentShaderID);
		GL20.glDeleteShader(this.vertexShaderID);
		GL20.glDeleteShader(this.fragmentShaderID);
		GL20.glDeleteProgram(this.programID);
	}

	public abstract void bindAllAttributes();

	public void bindAttribute(int index, String location) {
		GL20.glBindAttribLocation(this.programID, index, location);
	}

	private int createSourceCompileCheckShader(String filePath, int type, String name) {
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, FileReaderWriter.readFile(filePath));
		GL20.glCompileShader(shaderID);

		if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			log.error(String.format("could not compile %s shader which was loaded from %s. info log: %s", name,
					this.vertexFile, GL20.glGetShaderInfoLog(shaderID)));
		}
		return shaderID;
	}

	private void checkForProgrammError(int id, int type, String name) {
		if (GL20.glGetProgrami(id, type) == GL11.GL_FALSE) {
			log.error(String.format("could not %s program. info log: %s", name, GL20.glGetProgramInfoLog(id)));
		}
	}
}
