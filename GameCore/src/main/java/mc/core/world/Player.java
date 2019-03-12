package mc.core.world;

import lombok.AccessLevel;
import lombok.Getter;
import mc.core.event.Event;
import mc.core.event.EventProvider;
import mc.core.world.event.PlayerEvent;

@Getter
public class Player {

	private double x;
	private double y;
	private double z;

	private double pitch;
	private double yaw;

	@Getter(value = AccessLevel.PRIVATE)
	private Event<PlayerEvent> onUpdate = new Event<>();
	

	public void setPosition(double x, double y, double z) {
		if (this.x != x || this.y != y || this.z != z) {
			PlayerEvent event = new PlayerEvent(this.x, this.y, this.z, x, y, z);
			this.onUpdate.invoke(this, event);
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	public void setCamera(double pitch, double yaw) {
		if(this.pitch != pitch || this.yaw != yaw) {
			PlayerEvent event = new PlayerEvent(this.pitch, this.yaw, pitch, yaw);
			this.onUpdate.invoke(this, event);
			this.pitch = pitch;
			this.yaw = yaw;
		}
	}

	public EventProvider<PlayerEvent> OnUpdate() {
		return this.onUpdate;
	}
}
