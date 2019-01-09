package dhbw.engine.impl.display.render;

import java.nio.FloatBuffer;
import java.util.List;

import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
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
	private int vertexCount;
	@Getter(AccessLevel.PRIVATE)
	private float[] vertices;

	public Model(List<Vector3f> vertex) {
		this.vertices = getVertices(vertex);
		this.vertexCount = vertex.size();
	}

	public void create() {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(getVertexCount() * 3);
		buffer.put(this.vertices);
		buffer.flip();
		this.vertexArrayID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(this.vertexArrayID);
		this.vertexBufferID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.vertexBufferID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW); // change for motion
		GL20.glEnableVertexAttribArray(0);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		GL30.glBindVertexArray(0);
		GL20.glDisableVertexAttribArray(0);
	}

	public void remove() {
		GL30.glDeleteVertexArrays(this.vertexArrayID);
		GL15.glDeleteBuffers(this.vertexBufferID);
	}

	public int getVertexCount() {
		return this.vertexCount;
	}

	private float[] getVertices(List<Vector3f> vertex) {
		float[] vertices = new float[vertex.size() * 3];
		int count = 0;
		for (Vector3f vector : vertex) {
			vertices[count] = vector.x;
			vertices[count + 1] = vector.y;
			vertices[count + 2] = vector.z;
			count += 3;
		}
		return vertices;
	}
}
