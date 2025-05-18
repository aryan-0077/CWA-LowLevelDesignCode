package Good_Code;

public class Application {
    public void run() {
        Logger logger = Logger.getLogger(); // Always fetch the same instance
        logger.log("Application started.");
    }
}