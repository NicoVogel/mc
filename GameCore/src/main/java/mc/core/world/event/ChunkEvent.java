package mc.core.world;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChunkEvent {

	private int posX;
	private int posY;
	private ChunkEventType type;
}
