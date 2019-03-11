package mc.core.component;

import org.joml.Vector3d;

import lombok.Getter;
import lombok.Setter;
import mc.core.Component;
import mc.core.ComponentBinding;
import mc.core.ComponentCollection;

@Getter
public class Block extends Component {

	private int id;
	@Setter
	private String name;
	@Setter
	private String description;
	private Texture texture;
	private WorldPosition position;
	private ComponentBinding binding;

	public Block(ComponentCollection parent, Vector3d position, String texturePath) {
		super(parent);
		if (parent == null) {
			// TODO log
			return;
		}
		this.position = new WorldPosition(parent, position);
		this.texture = new Texture(parent, texturePath);
		this.binding = new ComponentBinding(parent, this, this.position, this.texture);
	}

	@Override
	public void close() {
		if (this.isClosed())
			return;
		super.close();
		this.binding.close();
	}

}
