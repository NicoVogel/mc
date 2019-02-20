package mc.logging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingEngine {

	public static void localLog() {
		log.debug("debug");
	}

	public static void provideLog() {
		log.error("error");
	}
}
