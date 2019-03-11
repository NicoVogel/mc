package mc.core.world;

import lombok.AccessLevel;
import lombok.Getter;
import mc.core.event.Event;
import mc.core.event.EventProvider;

@Getter
public class Player {

	private double x;
	private double y;
	private double z;

	private double pitch;
	private double yaw;

	@Getter(value = AccessLevel.PRIVATE)
	private Event<PlayerEvent> onUpdatePosition = new Event<>();

	public void setPosition(int x, int y, int z) {
		if (this.x != x && this.y != y && this.z != z) {
			PlayerEvent event = new PlayerEvent(this.x, this.y, this.z, x, y, z);
			this.onUpdatePosition.invoke(this, event);
		}
	}

	public EventProvider<PlayerEvent> OnUpdatePosition() {
		return this.onUpdatePosition;
	}
}
