package mc.core.world.event;

import org.joml.Vector3d;

import lombok.Getter;

@Getter
public class WorldObjectEvent extends PlayerViewEvent {

	private WorldObjectEventType type;
	private Vector3d position;

	public WorldObjectEvent() {
		super(false);

	}

}
