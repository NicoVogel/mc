package mc.core;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import mc.core.event.Disposable;
import mc.core.event.EventProvider;
import mc.core.factory.GameFactory;
import one.util.streamex.StreamEx;

/**
 * this is the basic object used by the engine
 * 
 * @author Nico
 *
 */
public final class GameObject implements Disposable, ComponentOrganizer, ElementStatus {

	@Getter
	@Setter
	private boolean active = true;

	@Getter
	private boolean closed = false;

	@Getter
	@Setter
	private String tag;

	@Getter
	private UUID id = UUID.randomUUID();

	@Getter
	private ComponentOrganizer children;

	public GameObject() {
		this.children = GameFactory.Instance().createComponentOrganizer();
	}

	/**
	 * creates a new component and adds it to its components
	 */
	public Component addComponent() {
		if (this.isClosed())
			// TODO add log
			return null;
		Component component = new Component(this);
		addComponent(component);
		return component;
	}

	@Override
	public void dispose() {
		if (this.isClosed())
			// TODO add log
			return;
		this.closed = true;
		this.active = false;
		this.children.dispose();
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
