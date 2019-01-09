package dhbw.engine.impl.display.render;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class Model {
	private int vertexArrayID;
	private int vertexBufferID;
	private int indicesBufferID;
	private int vertexCount;
	@Getter(AccessLevel.PRIVATE)
	private float[] vertices;
	private int[] indices;

	public Model(float[][] vertex, int[] indices) {
		this.vertices = getVertices(vertex);
		this.vertexCount = indices.length;
		this.indices = indices;
	}

	public void create() {

		FloatBuffer buffer = GLUtils.createPutFlipBuffer(this.vertices.length, this.vertices);
		IntBuffer indeciesBuffer = GLUtils.createPutFlipBuffer(this.indices.length, this.indices);
		this.vertexArrayID = GL30.glGenVertexArrays();

		GLUtils.gl30_bindVertex(this.vertexArrayID, () -> {

			this.vertexBufferID = GLUtils.gl15_GenBindDataBuffer(buffer, GL15.GL_ARRAY_BUFFER, GL15.GL_STATIC_DRAW);
			this.indicesBufferID = GLUtils.gl15_GenBindDataBuffer(indeciesBuffer, GL15.GL_ELEMENT_ARRAY_BUFFER,
					GL15.GL_STATIC_DRAW);

			GLUtils.gl20_enableVertex(0, () -> {
				GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
			});

		});

	}

	public void remove() {
		GL30.glDeleteVertexArrays(this.vertexArrayID);
		GL15.glDeleteBuffers(this.vertexBufferID);
		GL15.glDeleteBuffers(this.indicesBufferID);
	}

	public int getVertexCount() {
		return this.vertexCount;
	}

	private float[] getVertices(float[][] vertex) {
		float[] vertices = new float[vertex.length * 3];
		int count = 0;
		for (float[] vector : vertex) {
			vertices[count] = vector[0];
			vertices[count + 1] = vector[1];
			vertices[count + 2] = vector[2];
			count += 3;
		}
		return vertices;
	}
}
