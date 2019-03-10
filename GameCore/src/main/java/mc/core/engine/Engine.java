package mc.core.engine;

import java.util.Queue;
import java.util.stream.Stream;

import mc.core.engine.model.InputEvent;
import mc.core.world.Chunk;

public interface Engine {

	Queue<InputEvent> getEventQueue();

	Stream<Chunk> getChuncs();

	double getPlayerX();

	double getPlayerY();

	double getPlayerZ();

	double getCameraPitch();

	double getCameraYaw();

}
