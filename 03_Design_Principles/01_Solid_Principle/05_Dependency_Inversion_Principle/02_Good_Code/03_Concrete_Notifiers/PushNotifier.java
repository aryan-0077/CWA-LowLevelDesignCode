package Dependency_Inversion_Principle.Good_Code.Concrete_Notifiers;

import Dependency_Inversion_Principle.Good_Code.Interfaces.NotificationService;

public class PushNotifier implements NotificationService {
    @Override
    public void sendNotification(String message) {
        // Push notification specific implementation
    }
}