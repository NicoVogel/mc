package mc.engine.impl.display.render;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class GLUtils {

	/**
	 * enables and disables VertexAttribArray<br>
	 * <b>GL20.glEnableVertexAttribArray</b><br>
	 * <b>GL20.glDisableVertexAttribArray</b>
	 * 
	 * @param index
	 * @param work
	 */
	public static void gl20_enableVertex(int index, InsideWork work) {
		GL20.glEnableVertexAttribArray(index);
		work.work();
		GL20.glDisableVertexAttribArray(index);
	}

	/**
	 * binds vertex to an array and after that it releases it <br>
	 * <b>GL30.glBindVertexArray</b>
	 * 
	 * @param index
	 * @param work
	 */
	public static void gl30_bindVertex(int index, InsideWork work) {
		GL30.glBindVertexArray(index);
		work.work();
		GL30.glBindVertexArray(0);
	}

	/**
	 * generates a buffer, binds the buffer to the generated id and sets the data
	 * 
	 * @param buffer
	 * @param target
	 * @param usage
	 * @return buffer id
	 */
	public static int gl15_GenBindDataBuffer(FloatBuffer buffer, int target, int usage) {
		int bufferId = GL15.glGenBuffers();
		GL15.glBindBuffer(target, bufferId);
		GL15.glBufferData(target, buffer, usage);
		return bufferId;
	}

	/**
	 * generates a buffer, binds the buffer to the generated id and sets the data
	 * 
	 * @param buffer
	 * @param target
	 * @param usage
	 * @return buffer id
	 */
	public static int gl15_GenBindDataBuffer(IntBuffer buffer, int target, int usage) {
		int bufferId = GL15.glGenBuffers();
		GL15.glBindBuffer(target, bufferId);
		GL15.glBufferData(target, buffer, usage);
		return bufferId;
	}

	public static FloatBuffer createPutFlipBuffer(int capacity, float[] src) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(capacity);
		buffer.put(src);
		buffer.flip();
		return buffer;
	}

	public static IntBuffer createPutFlipBuffer(int capacity, int[] src) {
		IntBuffer buffer = BufferUtils.createIntBuffer(capacity);
		buffer.put(src);
		buffer.flip();
		return buffer;
	}

}
