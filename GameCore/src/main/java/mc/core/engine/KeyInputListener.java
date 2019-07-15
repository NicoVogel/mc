package mc.core.engine;

import mc.core.engine.model.KeyEvent;
import mc.core.event.interfaces.EventListener;

@FunctionalInterface
public interface KeyInputListener extends EventListener<KeyEvent> {

}
