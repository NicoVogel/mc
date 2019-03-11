package mc.core.engine;

import java.util.Queue;
import java.util.stream.Stream;

import mc.core.engine.model.InputEvent;
import mc.core.world.Chunk;
import mc.core.world.Player;

public interface Engine {

	Queue<InputEvent> getEventQueue();

	Stream<Chunk> getChuncs();

	Player getPlayer();

}
