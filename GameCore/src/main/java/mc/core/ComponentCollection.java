package mc.core;

import java.util.ArrayList;
import java.util.List;

public class ComponentCollection {
	private List<Component> components;

	protected List<Component> getComps() {
		if (this.components == null) {
			this.components = new ArrayList<>();
		}
		return this.components;
	}

	/* package */ void addComponent(Component component) {
		getComps().add(component);
	}

	/* package */ void removeComponent(Component component) {
		getComps().remove(component);
	}
}
