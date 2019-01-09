package dhbw.engine;

import java.util.List;

public interface Component {

	void action();

	void add(Component comp);

	void remove(Component comp);

	List<Component> getChildren();

	<T extends Component> List<T> getChildren(Class<T> type);
}
