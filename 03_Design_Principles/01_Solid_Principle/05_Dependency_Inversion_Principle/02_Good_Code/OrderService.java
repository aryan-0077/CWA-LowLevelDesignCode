package Dependency_Inversion_Principle.Good_Code;

import Dependency_Inversion_Principle.Good_Code.Concrete_Loggers.DatabaseLogger;
import Dependency_Inversion_Principle.Good_Code.Concrete_Notifiers.EmailNotifier;
import Dependency_Inversion_Principle.Good_Code.Core_Classes.Order;
import Dependency_Inversion_Principle.Good_Code.Interfaces.*;

public class OrderService {
    private final NotificationService notificationService;
    private final LoggingService loggingService;
    private final InventoryService inventoryService;
    // Constructor injection of dependencies
    public OrderService(NotificationService notificationService,
                        LoggingService loggingService, InventoryService inventoryService) {
        this.notificationService = notificationService;
        this.loggingService = loggingService;
        this.inventoryService = inventoryService;
    }

    public void placeOrder(Order order) {
        try {
            // Check inventory
            if (inventoryService.checkAvailability(order.getProduct())) {
                // Process order
                inventoryService.updateStock(order);
                // Send notification
                notificationService.sendNotification(
                        "Order #" + order.getId() + " placed successfully");
                // Log success
                loggingService.logMessage(
                        "Order processed successfully: " + order.getId());
            }
        } catch (Exception e) {
            loggingService.logError(
                    "Error processing order: " + order.getId() + " - " + e.getMessage());
            throw e;
        }
    }
}