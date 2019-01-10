package mc.engine;

import java.util.EventObject;

@FunctionalInterface
public interface EventListener<EVENT extends EventObject> {

	void invoke(EVENT e);

}
