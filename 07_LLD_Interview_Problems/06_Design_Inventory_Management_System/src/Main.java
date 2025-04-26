import CommonEnum.ProductCategory;
import InventoryStockManager.InventoryManager;
import ProductReplenishmentStrategies.ConcreteReplenishmentStrategies.BulkOrderStrategy;
import ProductReplenishmentStrategies.ConcreteReplenishmentStrategies.JustInTimeStrategy;
import ProductReplenishmentStrategies.ReplenishmentStrategy;
import ProductsFactory.Product;
import ProductsFactory.ProductFactory;
import UtilityClasses.Warehouse;

public class Main {
    public static void main(String[] args) {
        // Get the singleton instance of InventoryManager
        ReplenishmentStrategy replenishmentStrategy = new JustInTimeStrategy();
        InventoryManager inventoryManager = InventoryManager.getInstance(replenishmentStrategy);

        // Create and add warehouses
        Warehouse warehouse1 = new Warehouse("Warehouse 1");
        Warehouse warehouse2 = new Warehouse("Warehouse 2");
        inventoryManager.addWarehouse(warehouse1);
        inventoryManager.addWarehouse(warehouse2);

        // Create products using ProductFactory
        ProductFactory productFactory = new ProductFactory();
        Product laptop = productFactory.createProduct(
                ProductCategory.ELECTRONICS, "SKU123", "Laptop", 1000.0, 50, 25);
        Product tShirt = productFactory.createProduct(
                ProductCategory.CLOTHING, "SKU456", "T-Shirt", 20.0, 200, 100);
        Product apple = productFactory.createProduct(
                ProductCategory.GROCERY, "SKU789", "Apple", 1.0, 100, 200);

        // Add products to warehouses
        warehouse1.addProduct(laptop, 15);
        warehouse1.addProduct(tShirt, 20);
        warehouse2.addProduct(apple, 50);

        // Set replenishment strategy to Just-In-Time
        inventoryManager.setReplenishmentStrategy(new JustInTimeStrategy());

        // Perform inventory check and replenish if needed
        inventoryManager.performInventoryCheck();

        // Switch replenishment strategy to Bulk Order
        inventoryManager.setReplenishmentStrategy(new BulkOrderStrategy());

        // Replenish a specific product if needed
        inventoryManager.checkAndReplenish("SKU123");
    }
}

/*

Output :

15 units of Laptop (SKU: SKU123) added to Warehouse 1. New quantity: 15
20 units of T-Shirt (SKU: SKU456) added to Warehouse 1. New quantity: 20
50 units of Apple (SKU: SKU789) added to Warehouse 2. New quantity: 50


*/