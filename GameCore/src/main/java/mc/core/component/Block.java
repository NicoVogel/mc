package mc.core.component;

import lombok.Getter;
import mc.core.Component;
import mc.core.ComponentCollection;

@Getter
public class Block extends Component {

	private Texture texture;
	private WorldPosition position;

	public Block(ComponentCollection parent, int x, int y, int z, String texture) {
		super(parent);
		if (parent == null) {
			// TODO log
			return;
		}
		this.position = new WorldPosition(parent, x, y, z);
		this.texture = new Texture(parent, texture);
	}

	@Override
	public void close() {
		if (this.isClosed())
			return;
		super.close();
		this.texture.close();
		this.position.close();
	}

}
