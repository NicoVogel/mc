package mc.core;

import java.io.Closeable;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Component implements Closeable {

	@Setter
	private boolean active;
	private ComponentCollection parent;

	public Component(ComponentCollection parent) {
		if (parent == null) {
			this.active = false;
			// TODO add logger
			return;
		}
		this.parent = parent;
		this.parent.addComponent(this);
		this.active = true;
	}

	@Override
	public void close() {
		this.parent.removeComponent(this);
	}

}
