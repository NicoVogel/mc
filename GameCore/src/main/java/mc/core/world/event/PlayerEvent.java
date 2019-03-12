package mc.core.world.event;

import lombok.Getter;

@Getter
public class PlayerEvent {

	private double oldX;
	private double oldY;
	private double oldZ;

	private double newX;
	private double newY;
	private double newZ;

	private PlayerEventType type;
	
	private double oldPitch;
	private double oldYaw;
	
	private double newPitch;
	private double newYaw;
	
	public PlayerEvent(double oldX, double oldY, double oldZ, double newX, double newY, double newZ) {
		this.oldX = oldX;
		this.oldY = oldY;
		this.oldZ = oldZ;
		this.newX = newX;
		this.newY = newY;
		this.newZ = newZ;
		this.type = PlayerEventType.POSITION;
	}
	
	public PlayerEvent(double oldPitch, double oldYaw, double newPitch, double newYaw) {
		this.oldPitch = oldPitch;
		this.oldYaw = oldYaw;
		this.newPitch = newPitch;
		this.newYaw = newYaw;
		this.type = PlayerEventType.CAMERA;
	}
	
	public boolean isPositionEvent() {
		return this.type == PlayerEventType.POSITION;
	}
	
}
