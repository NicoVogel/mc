package mc.core;

import java.util.List;

public interface Composit {

	void action();

	void add(Composit comp);

	void remove(Composit comp);

	List<Composit> getChildren();

	<T extends Composit> List<T> getChildren(Class<T> type);
}
