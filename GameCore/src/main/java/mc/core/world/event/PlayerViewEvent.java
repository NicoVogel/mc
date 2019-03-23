package mc.core.world.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class PlayerViewEvent {

	private boolean isChunkEvent;

}
