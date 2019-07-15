package mc.core.world;

import org.joml.Vector2d;
import org.joml.Vector3d;

import lombok.AccessLevel;
import lombok.Getter;
import mc.core.event.impl.Event;
import mc.core.event.impl.OldNewValueEvent;
import mc.core.event.interfaces.Disposable;
import mc.core.event.interfaces.EventProvider;
import mc.core.util.Disposer;
@Getter
public class Player implements Disposable {

	private double x;
	private double y;
	private double z;

	private double pitch;
	private double yaw;

	@Getter(value = AccessLevel.PRIVATE)
	private Event<OldNewValueEvent<Vector3d>> onPositionUpdate = new Event<>();
	@Getter(value = AccessLevel.PRIVATE)
	private Event<OldNewValueEvent<Vector2d>> onCameraUpdate = new Event<>();
	@Getter(value = AccessLevel.PRIVATE)
	private Disposer dispose = new Disposer(this.onPositionUpdate, this.onCameraUpdate);

	public void setPosition(Vector3d position) {
		setPosition(position.x, position.y, position.z);
	}

	public Vector3d getPostionV() {
		return new Vector3d(this.x, this.y, this.z);
	}

	public void setPosition(double x, double y, double z) {
		if (this.x != x || this.y != y || this.z != z) {
			OldNewValueEvent<Vector3d> event = new OldNewValueEvent<Vector3d>(new Vector3d(this.x, this.y, this.z),
					new Vector3d(x, y, z));
			this.onPositionUpdate.invoke(this, event);
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	public void setCamera(Vector2d camera) {
		setCamera(camera.x, camera.y);
	}

	public Vector2d getCameraV() {
		return new Vector2d(this.pitch, this.yaw);
	}

	public void setCamera(double pitch, double yaw) {
		if (this.pitch != pitch || this.yaw != yaw) {
			OldNewValueEvent<Vector2d> event = new OldNewValueEvent<Vector2d>(new Vector2d(this.pitch, this.yaw),
					new Vector2d(pitch, yaw));
			this.onCameraUpdate.invoke(this, event);
			this.pitch = pitch;
			this.yaw = yaw;
		}
	}

	public EventProvider<OldNewValueEvent<Vector3d>> OnPositionUpdate() {
		return this.onPositionUpdate.getProvider();
	}

	public EventProvider<OldNewValueEvent<Vector2d>> OnCameraUpdate() {
		return this.onCameraUpdate.getProvider();
	}

	@Override
	public void dispose() {
		this.dispose.dispose();
	}
}
