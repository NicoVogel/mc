package mc.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * this is the basic object used by the engine
 * 
 * @author Nico
 *
 */
public class GameObject implements Composit {
	private List<Composit> composits;

	@Override
	public void action() {
	}

	@Override
	public void add(Composit comp) {
		getChildren().add(comp);
	}

	@Override
	public void remove(Composit comp) {
		getChildren().remove(comp);
	}

	@Override
	public List<Composit> getChildren() {
		if (this.composits == null) {
			this.composits = new ArrayList<>();
		}
		return this.composits;
	}

	@Override
	public <T extends Composit> List<T> getChildren(Class<T> clazz) {
		return getChildren().stream().filter(x -> clazz.isInstance(x)).map(x -> clazz.cast(x))
				.collect(Collectors.toList());
	}

}
