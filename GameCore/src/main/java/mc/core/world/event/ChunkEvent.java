package mc.core.world.event;

import lombok.Getter;

@Getter
public class ChunkEvent extends PlayerViewEvent {

	private int posX;
	private int posY;
	private ChunkEventType type;

	public ChunkEvent(int posX, int posY, ChunkEventType type) {
		super(true);
		this.posX = posX;
		this.posY = posY;
		this.type = type;
	}

}
