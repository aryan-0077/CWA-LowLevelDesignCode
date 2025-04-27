import CORPattern.ConcreteLogHandlers.*;
import CORPattern.LogHandler;
import LogAppenderStrategies.ConcreteLogAppenders.*;
import LogAppenderStrategies.LogAppender;
import CommonEnum.LogLevel;
import LoggerControllers.*;

public class Main {

    // Build the chain of loggers: INFO -> DEBUG -> ERROR
    private static LogHandler getChainOfLoggers(LogAppender appender) {
        LogHandler errorLogger = new ErrorLogger(LogHandler.ERROR, appender);
        LogHandler debugLogger = new DebugLogger(LogHandler.DEBUG, appender);
        LogHandler infoLogger = new InfoLogger(LogHandler.INFO, appender);
        infoLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(errorLogger);
        return infoLogger;
    }

    public static void main(String[] args) {
            // Select the log appender (console or file)
            LogAppender consoleAppender = new ConsoleAppender();
            LogAppender fileAppender = new FileAppender("logs.txt");
            // Create the chain of loggers with the console appender
            LogHandler loggerChain = getChainOfLoggers(consoleAppender);

            // Use a single logging approach to avoid duplication
            System.out.println("Logging INFO level message:");
            loggerChain.logMessage(LogHandler.INFO, "This is an information.");
            System.out.println("nLogging DEBUG level message:");
            loggerChain.logMessage(LogHandler.DEBUG, "This is a debug level information.");
            System.out.println("nLogging ERROR level message:");
            loggerChain.logMessage(LogHandler.ERROR, "This is an error information.");

            // Demonstrate the singleton Logger usage as an alternative
            System.out.println("nUsing Singleton Logger:");
            Logger logger = Logger.getInstance(LogLevel.INFO, consoleAppender);
            logger.setConfig(new LoggerConfig(LogLevel.INFO, fileAppender));
            logger.error("Using singleton Logger - Error message");
        }
}

/*

Output :

Logging INFO level message:
[INFO] 1745687847121 - This is an information.
INFO: This is an information.
nLogging DEBUG level message:
[DEBUG] 1745687847142 - This is a debug level information.
DEBUG: This is a debug level information.
nLogging ERROR level message:
[ERROR] 1745687847142 - This is an error information.
ERROR: This is an error information.
nUsing Singleton Logger:

*/
