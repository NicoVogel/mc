package mc.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.Setter;

/**
 * this is the basic object used by the engine
 * 
 * @author Nico
 *
 */
public class GameObject {

	@Getter
	@Setter
	private boolean active = true;
	private List<Component> components;
	private HashSet<Class<?>> compTypes;

	private List<Component> getComps() {
		if (this.components == null) {
			this.components = new ArrayList<>();
		}
		return this.components;
	}

	private HashSet<Class<?>> getCompTypes() {
		if (this.compTypes == null) {
			this.compTypes = new HashSet<>();
		}
		return this.compTypes;
	}

	/**
	 * Get all components, this is a clone of the original list
	 * 
	 * @return
	 */
	public List<Component> getComponents() {
		return new ArrayList<>(getComps());
	}

	/**
	 * Get all component types, this is a clone of the original hash set
	 * 
	 * @return
	 */
	public ArrayList<Class<?>> getComponentTypes() {
		return new ArrayList<>(getCompTypes());
	}

	/**
	 * check if a specific component is in the
	 * 
	 * @param clazz
	 * @return
	 */
	public <T> boolean hasA(Class<T> clazz) {
		return getCompTypes().contains(clazz);
	}

	/**
	 * return all the components of a specific type
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getComponents(Class<T> clazz) {
		return (List<T>) getComps().stream().flatMap(o -> (clazz.isInstance(o)) ? Stream.of((T) o) : Stream.empty())
				.collect(Collectors.toList());
	}

	/* package */ void addComponent(Component component) {
		getComps().add(component);
		getCompTypes().add(component.getClass());
	}

	/* package */ void removeComponent(Component component) {
		getComps().remove(component);
		if (this.getComps().stream().anyMatch(x -> component.getClass().isInstance(x)) == false) {
			getCompTypes().remove(component.getClass());
		}
	}
}
