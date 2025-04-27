package CommonEnum;

public enum LogLevel {
    DEBUG(1),   // Debug-level messages (least severe)
    INFO(2),    // Informational messages
    ERROR(3)  ; // Error messages indicating failures

    // Numeric value associated with each log level
    private final int value;
    LogLevel(int value) {
        this.value = value;
    }

    // Getter method to retrieve the numeric value of a log level
    public int getValue() {
        return value;
    }

    // Method to compare log levels based on severity
    public boolean isGreaterOrEqual(LogLevel other) {
        return this.value >= other.value;
    }
}
