package mc.core.world;

import org.joml.Vector3d;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorldObject {

	private int id;
	private int state;
	private double x;
	private double y;
	private double z;
	
	public Vector3d getPosition() {
		return new Vector3d(this.x, this.y, this.z);
	}
	
	public void setPosition(Vector3d position) {
		this.x = position.x;
		this.y = position.y;
		this.z = position.z;
	}

}
