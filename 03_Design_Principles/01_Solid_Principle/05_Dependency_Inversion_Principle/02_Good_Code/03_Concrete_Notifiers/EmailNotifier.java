package Dependency_Inversion_Principle.Good_Code.Concrete_Notifiers;

import Dependency_Inversion_Principle.Good_Code.Interfaces.NotificationService;

public class EmailNotifier implements NotificationService {
    @Override
    public void sendNotification(String message) {
        // Email specific implementation
    }
}
