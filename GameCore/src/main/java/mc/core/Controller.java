package mc.core;

import java.util.List;

import lombok.Getter;
import mc.core.reflect.ReflectHelper;

public abstract class Controller<T extends Component> {

	@Getter
	private boolean canBeUsed;
	private Class<T> handles;

	@SuppressWarnings("unchecked")
	public Controller() {
		this.handles = (Class<T>) ReflectHelper.getFirstGenericParameter(this.getClass());
		if (this.handles != null) {
			this.canBeUsed = true;
		} else {

		}
	}

	/**
	 * process all components of this type and only if they are active
	 * 
	 * @param gameObject
	 */
	public void process(GameObject gameObject) {
		if (this.canBeUsed == false || gameObject == null || gameObject.isActive() == false) {
			return;
		}
		List<T> components = gameObject.getComponents(this.handles);
		for (T comp : components) {
			if (comp.isActive()) {
				handleComponent(comp);
			}
		}
	}

	protected abstract void handleComponent(T component);

}
