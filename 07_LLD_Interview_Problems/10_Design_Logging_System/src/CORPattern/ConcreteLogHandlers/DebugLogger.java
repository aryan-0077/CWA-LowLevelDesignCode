package CORPattern.ConcreteLogHandlers;

import CORPattern.LogHandler;
import LogAppenderStrategies.LogAppender;

public class DebugLogger extends LogHandler {

    public DebugLogger(int level, LogAppender appender) {
        super(level, appender);
    }

    @Override
    protected void write(String message) {
        System.out.println("DEBUG: " + message);
    }
}
