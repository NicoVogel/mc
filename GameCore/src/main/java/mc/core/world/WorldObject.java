package mc.core.world;

import org.joml.Vector3d;

import lombok.AccessLevel;
import lombok.Getter;
import mc.core.event.Disposable;
import mc.core.event.Event;
import mc.core.event.EventProvider;
import mc.core.world.event.WorldObjectEventType;

@Getter
public class WorldObject implements Disposable {

	private int id;
	private int state;
	private double x;
	private double y;
	private double z;
	private Chunk parent;
	@Getter(value = AccessLevel.PRIVATE)
	private Event<WorldObjectEventType> stateChanged;

	public WorldObject(int id, int state, Chunk chunk, Vector3d position) {
		this(id, state, chunk, position.x, position.y, position.z);
	}

	public WorldObject(int id, int state, Chunk chunk, double x, double y, double z) {
		this.id = id;
		this.state = state;
		this.parent = chunk;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3d getPosition() {
		return new Vector3d(this.x, this.y, this.z);
	}

	public void setState(int state) {
		if (this.state != state) {
			this.state = state;
			this.stateChanged.invoke(this, WorldObjectEventType.UPDATE);
		}
	}

	public EventProvider<WorldObjectEventType> OnStateChanged() {
		return this.stateChanged.getProvider();
	}

	@Override
	public void dispose() {
		this.stateChanged.dispose();
	}

}
