package mc.core;

import java.util.HashSet;
import java.util.Set;

import mc.core.event.Event;
import mc.core.event.EventProvider;

public class ComponentCollection {
	private Set<Component> components;
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

	protected Set<Component> getComps() {
		if (this.components == null) {
			this.components = new HashSet<>();
		}
		return this.components;
	}

	/* package */ void addComponent(Component component) {
		if (getComps().add(component)) {
			this.onAdd.invoke(this, component);
		}
	}

	/* package */ void removeComponent(Component component) {
		if (getComps().remove(component)) {
			this.onRemove.invoke(this, component);
		}
	}
}
