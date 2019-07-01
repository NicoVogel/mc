package mc.core.component;

import org.joml.Vector3d;

import lombok.Getter;
import lombok.Setter;
import mc.core.Component;
import mc.core.ComponentCollectionImpl;

@Getter
@Setter
public class WorldPosition extends Component {

	private Vector3d position;

	public WorldPosition(ComponentCollectionImpl parent, double x, double y, double z) {
		this(parent, new Vector3d(x,y,z));
	}
	
	public WorldPosition(ComponentCollectionImpl parent, Vector3d position) {
		super(parent);
		this.position = position;
	}

}
