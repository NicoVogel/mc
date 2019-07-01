package mc.core.component;

import lombok.Getter;
import lombok.Setter;
import mc.core.Component;
import mc.core.ComponentCollectionImpl;

@Getter
public class Item extends Component {

	private int id;
	@Setter
	private String name;
	@Setter
	private String description;
	
	
	public Item(ComponentCollectionImpl parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

}
