package mc.core;

import java.io.Closeable;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Component implements Closeable {

	@Setter
	private boolean active;
	private GameObject parent;

	public Component(GameObject parent) {
		this.parent = parent;
		this.parent.addComponent(this);
		this.active = true;
	}

	@Override
	public void close() {
		this.parent.removeComponent(this);
	}

}
