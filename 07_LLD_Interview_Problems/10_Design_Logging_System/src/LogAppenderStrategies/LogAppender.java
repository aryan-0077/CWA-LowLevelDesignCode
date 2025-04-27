package LogAppenderStrategies;

import UtilityClasses.LogMessage;

public interface LogAppender {
    void append(LogMessage logMessage);
}
