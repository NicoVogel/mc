package mc.core.world;

import java.util.Map;

import org.joml.Vector2i;

import lombok.Getter;

@Getter
public class World {

	public final int chunkWidth;
	public final int chunkLength;
	public final int chunkHeight;

	public Map<Vector2i, Chunk> chunks;
	
	public World(int width, int length,int height) {
		this.chunkWidth = width;
		this.chunkLength = length;
		this.chunkHeight = height;
	}


}
