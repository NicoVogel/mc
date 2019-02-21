package mc.core;

import java.io.Closeable;

import lombok.Getter;

@Getter
public abstract class Component implements Closeable {

	private boolean active;
	private boolean closed;
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

	/**
	 * used to remove the component from its parent and disables itself
	 */
	@Override
	public void close() {
		if (this.isClosed())
			return;
		this.parent.removeComponent(this);
		this.closed = true;
		this.active = false;
	}

	public void setActive(boolean active) {
		if (this.closed == false) {
			this.active = active;
		}
	}

}
