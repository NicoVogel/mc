package mc.core.world;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2i;

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
	}

	public WorldObject[][][] getBlocks() {
		if (this.blocks == null) {
			this.blocks = new WorldObject[this.parent.getChunkWidth()][this.parent.getChunkHeight()][this.parent
					.getChunkLength()];
		}
		return this.blocks;
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
