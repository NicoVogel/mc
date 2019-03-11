package mc.core.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.joml.Vector2i;
import org.joml.Vector3i;

import lombok.Getter;
import mc.core.event.Event;
import mc.core.event.EventProvider;

public class Chunk {

	public static final int WIDTH = 16;
	public static final int LENGTH = 16;
	public static final int HEIGHT = 256;

	@Getter
	private Vector2i position;
	private Map<Vector3i, WorldObject> blocks;
	private List<WorldObject> items;
	private Event<ChunkEvent> onUpdate = new Event<>();

	public Chunk(Vector2i position) {
		this.position = position;
	}

	public Chunk(int x, int y) {
		this(new Vector2i(x, y));
	}

	public Map<Vector3i, WorldObject> getBlocks() {
		if (this.blocks == null) {
			this.blocks = new TreeMap<>();
		}
		return this.blocks;
	}

	public List<WorldObject> getItems() {
		if (this.items == null) {
			this.items = new ArrayList<>();
		}
		return this.items;
	}

	public EventProvider<ChunkEvent> OnUpdate() {
		return this.onUpdate;
	}

}
