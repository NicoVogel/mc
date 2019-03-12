package mc.core.world;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;
import org.joml.Vector3i;

import lombok.Getter;
import mc.core.event.Disposable;
import mc.core.event.Event;
import mc.core.event.EventProvider;
import mc.core.world.event.ChunkEvent;

public class Chunk implements Disposable {

	@Getter
	private int x;
	@Getter
	private int y;
	private WorldObject[][][] blocks;
	private List<WorldObject> items;
	private Event<ChunkEvent> onUpdate = new Event<>();
	private World parent;

	public Chunk(World parent, Vector2i position) {
		this(parent, position.x, position.y);

	}

	public Chunk(World parent, int x, int y) {
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.blocks = new WorldObject[this.parent.getChunkWidth()][this.parent.getChunkHeight()][this.parent
				.getChunkLength()];
	}

	public WorldObject getBlock(Vector3i position) {
		return getBlock(position.x, position.y, position.z);
	}

	public WorldObject getBlock(int x, int y, int z) {
		return this.blocks[x][y][z];
	}

	public void setBlock(WorldObject wo, Vector3i position) {
		setBlock(wo, position.x, position.y, position.z);
	}

	public void setBlock(WorldObject wo, int x, int y, int z) {
		this.blocks[x][y][z] = wo;
	}

	public List<WorldObject> getItems() {
		if (this.items == null) {
			this.items = new ArrayList<>();
		}
		return this.items;
	}

	public Vector2i getPosition() {
		return new Vector2i(this.x, this.y);
	}

	public EventProvider<ChunkEvent> OnUpdate() {
		return this.onUpdate;
	}

	@Override
	public void dispose() {
		this.onUpdate.dispose();
	}

}
