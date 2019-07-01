package mc.core.component;

import lombok.Getter;
import lombok.Setter;
import mc.core.Component;
import mc.core.ComponentCollectionImpl;

public class Texture extends Component {

	@Getter
	@Setter
	private String path;

	public Texture(ComponentCollectionImpl parent) {
		super(parent);
	}

	public Texture(ComponentCollectionImpl parent, String path) {
		this(parent);
		this.path = path;
	}

}
