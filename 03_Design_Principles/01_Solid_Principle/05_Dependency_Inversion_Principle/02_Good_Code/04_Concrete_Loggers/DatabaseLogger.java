package Dependency_Inversion_Principle.Good_Code.Concrete_Loggers;

import Dependency_Inversion_Principle.Good_Code.Interfaces.LoggingService;

public class DatabaseLogger implements LoggingService {
    @Override
    public void logMessage(String message) {
        // Database logging implementation
    }

    @Override
    public void logError(String error) {
        // Error logging implementation
    }
}
