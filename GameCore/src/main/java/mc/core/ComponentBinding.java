package mc.core;

import lombok.Getter;
import mc.core.event.EventListener;

public class ComponentBinding implements EventListener<Component> {

	@Getter
	private boolean bindingActive;
	private ComponentCollection parent;
	private Component main;
	private Component[] bindedComponents;

	public ComponentBinding(ComponentCollection parent, Component main, Component... binded) {
		this.main = main;
		this.bindedComponents = binded;
		this.parent = parent;
		this.parent.OnRemove().add(this);
		this.bindingActive = true;
	}

	public void cancelBinding() {
		this.parent.OnRemove().remove(this);
		this.bindingActive = false;
	}

	public void dispose() {
		this.main.dispose();
		for (Component component : this.bindedComponents) {
			component.dispose();
		}
		cancelBinding();
	}

	@Override
	public void listen(Object sender, Component object) {
		for (Component component : this.bindedComponents) {
			if (object == component) {
				dispose();
				return;
			}
		}
	}
}
