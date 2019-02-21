package mc.logging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingEngine {

	public static Logger getLogger(Class<?> clazz) {
		return new Logger(clazz);
	}

	public static void localLog() {
		log.debug("debug");
	}

	public static void provideLog() {
		log.error("error");
	}
}
