package mc.core.world.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mc.core.world.Chunk;
import mc.core.world.WorldObject;

@Getter
@AllArgsConstructor
public class WorldObjectEvent {

	private WorldObjectEventType type;
	private WorldObject reference;
	private Chunk chunk;
	
}
