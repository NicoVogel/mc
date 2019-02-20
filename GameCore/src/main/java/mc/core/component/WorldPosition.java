package mc.core.component;

import lombok.Getter;
import lombok.Setter;
import mc.core.Component;
import mc.core.GameObject;

@Getter
@Setter
public class WorldPosition extends Component {

	private int x;
	private int y;
	private int z;

	public WorldPosition(GameObject parent) {
		super(parent);
	}

}
