package dhbw.engine;

import java.util.List;

/**
 * this is the basic object used by the engine
 * 
 * @author Nico
 *
 */
public class GameObject implements Composit {

	@Override
	public void action() {
	}

	@Override
	public void add(Composit comp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Composit comp) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Composit> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Composit> List<T> getChildren(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

}
