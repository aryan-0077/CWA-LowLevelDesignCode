package Good_Code.Thread_Safe_Logger;

public class Logger {
    private static volatile Logger instance; // volatile keyword ensures visibility across threads
    private Logger() {} // Private constructor to prevent instantiation

    public static Logger getInstance() {
        if (instance == null) { // First check (no synchronization needed here)
            synchronized (Logger.class) { // Synchronize only when creating the instance
                if (instance == null) { // Second check (inside synchronized block)
                    instance = new Logger(); // Create the instance if it's still null
                }
            }
        }
        return instance; // Return the single instance
    }

    public void log(String message) {
        System.out.println("Log: " + message);
    }
}
