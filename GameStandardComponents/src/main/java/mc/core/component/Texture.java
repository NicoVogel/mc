package mc.core.component;

import lombok.Getter;
import lombok.Setter;
import mc.core.Component;
import mc.core.ComponentCollection;

public class Texture extends Component {

	@Getter
	@Setter
	private String path;

	public Texture(ComponentCollection parent) {
		super(parent);
	}

	public Texture(ComponentCollection parent, String path) {
		this(parent);
		this.path = path;
	}

}
