package Good_Code;

public class Logger {
    private static Logger logger = null;
    private Logger() {} // Private constructor to prevent external instantiation

    public static Logger getLogger() {
        if (logger == null) {
            logger = new Logger(); // New instance only if one doesn't exist
        }
        return logger;
    }
    public void log(String message) {
        System.out.println("Log: " + message);
    }
}
