package mc.logging;

import java.util.Map;

import org.slf4j.event.Level;

public class LoggingEngine {

	/* package */ static Level sendToServerLevel = Level.ERROR;

	public static Logger getLogger(Class<?> clazz) {
		return new Logger(clazz);
	}

	public static void log(Level level, Class<?> sender, String logMessage, Map<String, Object> data) {
		if (level.compareTo(sendToServerLevel) < 0) {
			return;
		}
		// TODO
		// do the send to server stuff
	}

}
