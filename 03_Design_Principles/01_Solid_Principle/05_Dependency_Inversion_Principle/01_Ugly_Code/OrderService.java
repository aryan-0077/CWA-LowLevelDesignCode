package Dependency_Inversion_Principle.Ugly_Code;

public class OrderService {
    private EmailNotifier emailNotifier;
    private DatabaseLogger logger;
    private InventorySystem inventory;

    public OrderService() {
        // Direct dependencies on concrete implementations
        this.emailNotifier = new EmailNotifier();
        this.logger = new DatabaseLogger();
        this.inventory = new InventorySystem();
    }

    public void placeOrder(Order order) {
        // Process order
        inventory.updateStock(order);
        emailNotifier.sendEmail("Order #" + order.getId() + " placed successfully");
        logger.logTransaction("Order placed: " + order.getId());
    }
}