package UtilityClasses;

import CommonEnum.LogLevel;

public class LogMessage {
    // Log level of the message (e.g., INFO, DEBUG, ERROR)
    private final LogLevel level;
    // The actual log message content
    private final String message;
    // Timestamp when the log message was created
    private final long timestamp;
    // Constructor to initialize log level and message, setting the timestamp to current time
    public LogMessage(LogLevel level, String message) {
        this.level = level;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    // Returns the log level of the message
    public LogLevel getLevel() {
        return level;
    }

    // Returns the log message content
    public String getMessage() {
        return message;
    }

    // Returns the timestamp of the log creation
    public long getTimestamp() {
        return timestamp;
    }

    // Formats the log message as a string with level, timestamp, and message
    @Override
    public String toString() {
        return "[" + level + "] " + timestamp + " - " + message;
    }
}
