package mc.logging;

import java.util.logging.Level;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Logger {

	private Class<?> sender;

	public void log(Level level, LoggingTask log) {

	}

}
