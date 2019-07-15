package mc.core.model;

import lombok.AccessLevel;
import lombok.Getter;
import mc.core.Component;
import mc.core.GameObject;
import mc.core.event.interfaces.EventProvider;
import mc.core.factory.GameFactory;
import one.util.streamex.StreamEx;

@Getter
public class ComponentStandard implements Component {

	private boolean active;
	private boolean closed;
	private GameObject gameObject;
	private Component parent;
	private Tag tagObject;
	@Getter(value = AccessLevel.NONE)
	private ComponentCollection children;

	private ComponentStandard() {
		this.active = true;
		this.children = GameFactory.Instance().createComponentCollection(this);
	}

	/* package */ ComponentStandard(GameObject gameObject) {
		this();
		if (gameObject == null) {
			this.active = false;
			this.closed = false;
			// TODO log
			return;
		}
		this.gameObject = gameObject;
		this.gameObject.addComponent(this);
	}

	/* package */ ComponentStandard(Component component) {
		this();
		if (component == null) {
			this.active = false;
			this.closed = false;
			// TODO log
			return;
		}
		this.parent = component;
		this.parent.addComponent(this);
	}

	public GameObject getGameObject() {
		if (this.gameObject == null) {
			this.gameObject = this.getParent().getGameObject();
		}
		return this.gameObject;
	}

	public Tag getTagObject() {
		if (this.tagObject == null) {
			this.tagObject = new Tag("");
		}
		return this.tagObject;
	}

	public String getTag() {
		return getTagObject().getTag();
	}

	public void setTag(String tag) {
		getTagObject().setTag(tag);
	}

	/**
	 * used to remove the component from its parent and disables itself
	 */
	@Override
	public void dispose() {
		if (this.isClosed())
			return;
		this.closed = true;
		this.active = false;
		this.parent.removeComponent(this);
		this.children.dispose();
	}

	public void setActive(boolean active) {
		if (this.closed == false) {
			this.active = active;
		}
	}

	@Override
	public StreamEx<Component> getComponents() {
		return this.children.getComponents();
	}

	@Override
	public <T extends Component> T getComponent(Class<T> type) {
		return this.children.getComponent(type);
	}

	@Override
	public <T extends Component> T getComponentOfParent(Class<T> type) {
		return this.children.getComponentOfParent(type);
	}

	@Override
	public <T extends Component> T getComponentOfChildren(Class<T> type) {
		return this.children.getComponentOfChildren(type);
	}

	@Override
	public <T extends Component> StreamEx<T> getComponents(Class<T> type) {
		return this.children.getComponents(type);
	}

	@Override
	public <T extends Component> StreamEx<T> getComponentsOfParent(Class<T> type) {
		return this.children.getComponentsOfParent(type);
	}

	@Override
	public <T extends Component> StreamEx<T> getComponentsOfChildren(Class<T> type) {
		return this.children.getComponentsOfChildren(type);
	}

	@Override
	public <T extends Component> T getComponent(String tag) {
		return this.children.getComponent(tag);
	}

	@Override
	public <T extends Component> T getComponentOfParent(String tag) {
		return this.children.getComponentOfParent(tag);
	}

	@Override
	public <T extends Component> T getComponentOfChildren(String tag) {
		return this.children.getComponentOfChildren(tag);
	}

	@Override
	public <T extends Component> StreamEx<T> getComponents(String tag) {
		return this.children.getComponents(tag);
	}

	@Override
	public <T extends Component> StreamEx<T> getComponentsOfParent(String tag) {
		return this.children.getComponentsOfParent(tag);
	}

	@Override
	public <T extends Component> StreamEx<T> getComponentsOfChildren(String tag) {
		return this.children.getComponentsOfChildren(tag);
	}

	@Override
	public <T extends Component> T getComponent(Tag tag) {
		return this.children.getComponent(tag);
	}

	@Override
	public <T extends Component> T getComponentOfParent(Tag tag) {
		return this.children.getComponentOfParent(tag);
	}

	@Override
	public <T extends Component> T getComponentOfChildren(Tag tag) {
		return this.children.getComponentOfChildren(tag);
	}

	@Override
	public <T extends Component> StreamEx<T> getComponents(Tag tag) {
		return this.children.getComponents(tag);
	}

	@Override
	public <T extends Component> StreamEx<T> getComponentsOfParent(Tag tag) {
		return this.children.getComponentsOfParent(tag);
	}

	@Override
	public <T extends Component> StreamEx<T> getComponentsOfChildren(Tag tag) {
		return this.children.getComponentsOfChildren(tag);
	}

	@Override
	public <T extends Component> StreamEx<T> getComponents(WrapKey<T> key) {
		return this.children.getComponents(key);
	}

	@Override
	public <T extends Component> StreamEx<T> getComponentsOfChildren(WrapKey<T> key) {
		return this.children.getComponentsOfChildren(key);
	}

	@Override
	public <T extends Component> StreamEx<T> getComponentsOfParent(WrapKey<T> key) {
		return this.children.getComponentsOfParent(key);
	}

	@Override
	public void addComponent(Component component) {
		this.children.addComponent(component);
	}

	@Override
	public boolean removeComponent(Component component) {
		return this.children.removeComponent(component);
	}

	@Override
	public EventProvider<Component> OnAdd() {
		return this.children.OnAdd();
	}

	@Override
	public EventProvider<Component> OnRemove() {
		return this.children.OnRemove();
	}

}
