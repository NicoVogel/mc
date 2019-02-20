package mc.core.world;

import java.util.Map;
import java.util.TreeMap;

import org.joml.Vector2i;
import org.joml.Vector3i;

import lombok.Getter;
import mc.core.GameObject;

public class Chunk {

	public static final int WIDTH = 16;
	public static final int LENGTH = 16;
	public static final int HEIGHT = 256;

	@Getter
	private Vector2i position;
	private Map<Vector3i, GameObject> blocks;

	public Chunk(Vector2i position) {
		this.position = position;
	}

	public Chunk(int x, int y) {
		this(new Vector2i(x, y));
	}

	public Map<Vector3i, GameObject> getBlocks() {
		if (this.blocks == null) {
			this.blocks = new TreeMap<>();
		}
		return this.blocks;
	}

}
