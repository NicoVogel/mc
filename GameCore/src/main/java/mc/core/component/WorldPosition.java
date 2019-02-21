package mc.core.component;

import lombok.Getter;
import lombok.Setter;
import mc.core.Component;
import mc.core.ComponentCollection;

@Getter
@Setter
public class WorldPosition extends Component {

	private int x;
	private int y;
	private int z;

	public WorldPosition(ComponentCollection parent, int x, int y, int z) {
		super(parent);
	}

}
