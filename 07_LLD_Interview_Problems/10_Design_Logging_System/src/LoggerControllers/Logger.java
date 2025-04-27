package LoggerControllers;

import CommonEnum.LogLevel;
import LogAppenderStrategies.LogAppender;
import UtilityClasses.LogMessage;

import java.util.concurrent.ConcurrentHashMap;

public class Logger {
    private static final ConcurrentHashMap<String, Logger> instances = new ConcurrentHashMap<>();
    private LoggerConfig config;
    // Private constructor to enforce singleton pattern
    private Logger(LogLevel logLevel, LogAppender logAppender) {
        config = new LoggerConfig(logLevel, logAppender);
    }

    // Get instance based on LogLevel and LogAppender
    public static Logger getInstance(LogLevel logLevel, LogAppender logAppender) {
        String key = logLevel.name() + "_" + logAppender.getClass().getName();
        // Compute instance if absent (thread-safe lazy initialization)
        return instances.computeIfAbsent(key, k -> new Logger(logLevel, logAppender));
    }

    // Updates the logger configuration
    public void setConfig(LoggerConfig config) {
        synchronized (Logger.class) { // Ensure thread safety while updating config
            this.config = config;
        }
    }

    // Logs a message if the level meets the configured threshold
    public void log(LogLevel level, String message) {
        if (level.getValue() >= config.getLogLevel().getValue()) {
            LogMessage logMessage = new LogMessage(level, message);
            config.getLogAppender().append(logMessage);
        }
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
}
