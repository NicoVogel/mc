package mc.core.world.generation;

import org.joml.Vector2i;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mc.core.world.Chunk;
import mc.core.world.World;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorldGenerationManager {

	private static final WorldGenerationManager INSTANCE = new WorldGenerationManager();

	public static WorldGenerationManager getInstance() {
		return WorldGenerationManager.INSTANCE;
	}

	public Chunk generateChunk(World world, Vector2i position) {
		return new Chunk(world, position);
	}

}
