package mc.logging;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.event.Level;

import lombok.Getter;

public class Logger {

	private final org.slf4j.Logger log;

	@Getter
	private Class<?> sender;

	/* package */ Logger(Class<?> sender) {
		this.sender = sender;
		this.log = org.slf4j.LoggerFactory.getLogger(this.sender);
	}

	public void log(Level level, LoggingTask log) {
		this.log(level, log, null);
	}

	public void log(Level level, LoggingTask log, Map<String, Object> data) {
		switch (level) {
		case TRACE:
			trace(log, data);
			return;
		case DEBUG:
			debug(log, data);
			return;
		case INFO:
			info(log, data);
			return;
		case WARN:
			warn(log, data);
			return;
		case ERROR:
		default:
			error(log, data);
		}
	}

	public void error(LoggingTask log) {
		this.error(log, null);
	}

	public void error(LoggingTask log, Map<String, Object> data) {
		if (!this.log.isErrorEnabled()) {
			return;
		}

		this.log.error(buildLog(log, data));
		LoggingEngine.log(Level.ERROR, sender, log.log(), data);
	}

	public void warn(LoggingTask log) {
		this.warn(log, null);
	}

	public void warn(LoggingTask log, Map<String, Object> data) {
		if (!this.log.isWarnEnabled()) {
			return;
		}

		this.log.warn(buildLog(log, data));
		LoggingEngine.log(Level.WARN, sender, log.log(), data);
	}

	public void info(LoggingTask log) {
		this.info(log, null);
	}

	public void info(LoggingTask log, Map<String, Object> data) {
		if (!this.log.isInfoEnabled()) {
			return;
		}

		this.log.info(buildLog(log, data));
		LoggingEngine.log(Level.INFO, sender, log.log(), data);
	}

	public void debug(LoggingTask log) {
		this.debug(log, null);
	}

	public void debug(LoggingTask log, Map<String, Object> data) {
		if (!this.log.isDebugEnabled()) {
			return;
		}

		this.log.debug(buildLog(log, data));
		LoggingEngine.log(Level.DEBUG, sender, log.log(), data);
	}

	public void trace(LoggingTask log) {
		this.trace(log, null);
	}

	public void trace(LoggingTask log, Map<String, Object> data) {
		if (!this.log.isTraceEnabled()) {
			return;
		}

		this.log.trace(buildLog(log, data));
		LoggingEngine.log(Level.TRACE, sender, log.log(), data);
	}

	private String buildLog(LoggingTask log, Map<String, Object> data) {
		StringBuilder builder = new StringBuilder();
		builder.append(log.log());
		if (data != null) {
			builder.append('\n');
			for (Entry<String, Object> entitie : data.entrySet()) {
				builder.append(entitie.getKey());
				builder.append(":\t\t");
				builder.append(entitie.getValue().toString());
			}
		}
		return builder.toString();
	}

}
