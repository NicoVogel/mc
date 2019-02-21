package mc.core;

import java.util.ArrayList;
import java.util.List;

import mc.core.event.Event;
import mc.core.event.EventProvider;

public class ComponentCollection {
	private List<Component> components;
	private Event<Component> onAdd;
	private Event<Component> onRemove;

	public ComponentCollection() {
		this.onAdd = new Event<>();
		this.onRemove = new Event<>();
	}

	public EventProvider<Component> OnAdd() {
		return this.onAdd;
	}

	public EventProvider<Component> OnRemove() {
		return this.onRemove;
	}

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
