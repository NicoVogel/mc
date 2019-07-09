package mc.core.statemachine;

import lombok.Getter;
import mc.core.Component;
import mc.core.GameObject;
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
		gameObject.getComponents(this.handles).forEach(component -> {
			if (component.isActive()) {
				handleComponent(component);
			}
		});
	}

	protected abstract void handleComponent(T component);

}
