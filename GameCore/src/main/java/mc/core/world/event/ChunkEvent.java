package mc.core.world.event;

import lombok.Getter;
import mc.core.world.Chunk;

@Getter
public class ChunkEvent extends PlayerViewEvent {

	private int posX;
	private int posY;
	private ChunkEventType type;
	private Chunk chunk;

	public ChunkEvent(int posX, int posY, Chunk chunk) {
		this(posX, posY, chunk, ChunkEventType.ADD);
	}

	public ChunkEvent(int posX, int posY) {
		this(posX, posY, null, ChunkEventType.REMOVE);
	}

	private ChunkEvent(int posX, int posY, Chunk chunk, ChunkEventType type) {
		super(true);
		this.posX = posX;
		this.posY = posY;
		this.type = type;
	}
}
