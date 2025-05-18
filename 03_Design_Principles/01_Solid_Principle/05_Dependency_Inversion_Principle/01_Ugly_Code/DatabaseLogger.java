package Dependency_Inversion_Principle.Ugly_Code;

public class DatabaseLogger {
    public void logTransaction(String message){
        System.out.println(message);
    }
}
