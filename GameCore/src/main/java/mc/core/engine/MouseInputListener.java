package mc.core.engine;

import mc.core.engine.model.MouseEvent;
import mc.core.event.EventListener;

@FunctionalInterface
public interface MouseInputListener extends EventListener<MouseEvent> {

}
