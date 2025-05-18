package Dependency_Inversion_Principle.Good_Code.Interfaces;

public interface LoggingService {
    void logMessage(String message);
    void logError(String error);
}
