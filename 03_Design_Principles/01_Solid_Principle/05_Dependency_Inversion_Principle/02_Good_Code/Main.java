package Dependency_Inversion_Principle.Good_Code;

import Dependency_Inversion_Principle.Good_Code.Concrete_Inventories.WarehouseInventoryService;
import Dependency_Inversion_Principle.Good_Code.Concrete_Loggers.DatabaseLogger;
import Dependency_Inversion_Principle.Good_Code.Concrete_Notifiers.EmailNotifier;
import Dependency_Inversion_Principle.Good_Code.Interfaces.InventoryService;
import Dependency_Inversion_Principle.Good_Code.Interfaces.LoggingService;
import Dependency_Inversion_Principle.Good_Code.Interfaces.NotificationService;

public class Main {
    public static void main(String[] args) {
        // Usage with dependency injection
        NotificationService emailNotifier = new EmailNotifier();
        LoggingService logger = new DatabaseLogger();
        InventoryService inventory = new WarehouseInventoryService();
        OrderService orderService = new OrderService(emailNotifier, logger, inventory);
    }
}
