package mc.core.world;

import java.util.Map;
import java.util.TreeMap;

import org.joml.Vector2i;

import lombok.Getter;
import mc.core.world.generation.WorldGenerationManager;

@Getter
public class World {

	public final int chunkWidth;
	public final int chunkLength;
	public final int chunkHeight;

	public Map<Vector2i, Chunk> chunks;

	public World(int width, int length, int height) {
		this.chunkWidth = width;
		this.chunkLength = length;
		this.chunkHeight = height;
	}

	private Map<Vector2i, Chunk> getChunks() {
		if (this.chunks == null) {
			this.chunks = new TreeMap<>();
		}
		return this.chunks;
	}

	public Chunk getChunk(int x, int y) {
		return getChunk(new Vector2i(x, y));
	}

	public Chunk getChunk(Vector2i position) {
		return getChunks().computeIfAbsent(position,
				postion -> WorldGenerationManager.getInstance().generateChunk(this, position));
	}

}
