package mc.core.component;

import lombok.Getter;
import mc.core.Component;
import mc.core.ComponentBinding;
import mc.core.ComponentCollection;

@Getter
public class Block extends Component {

	private Texture texture;
	private WorldPosition position;
	private ComponentBinding binding;

	public Block(ComponentCollection parent, int x, int y, int z, String texture) {
		super(parent);
		if (parent == null) {
			// TODO log
			return;
		}
		this.position = new WorldPosition(parent, x, y, z);
		this.texture = new Texture(parent, texture);
		this.binding = new ComponentBinding(parent, this, this.position, this.texture);

		// if one of its needed components are removed for some reason close this

	}

	@Override
	public void close() {
		if (this.isClosed())
			return;
		super.close();
		this.binding.close();
	}

}
