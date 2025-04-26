package InventoryStockManager;

import ProductReplenishmentStrategies.ReplenishmentStrategy;
import ProductsFactory.Product;
import ProductsFactory.ProductFactory;
import UtilityClasses.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    // Singleton instance
    private static InventoryManager instance;

    // System components
    private List<Warehouse> warehouses;
    private ProductFactory productFactory;
    private ReplenishmentStrategy replenishmentStrategy;

    // Private constructor to prevent instantiation from outside
    private InventoryManager(ReplenishmentStrategy replenishmentStrategy) {
        // Initialize collections and dependencies
        warehouses = new ArrayList<>();
        productFactory = new ProductFactory();
        this.replenishmentStrategy = replenishmentStrategy;
    }

    // Static method to get the singleton instance with thread safety
    public static synchronized InventoryManager getInstance(ReplenishmentStrategy replenishmentStrategy) {
        if (instance == null) {
            instance = new InventoryManager(replenishmentStrategy);
        }
        return instance;
    }

    // Strategy pattern method
    public void setReplenishmentStrategy(ReplenishmentStrategy replenishmentStrategy) {
        this.replenishmentStrategy = replenishmentStrategy;
    }

    // Warehouse management
    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }

    public void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
    }

    // Product inventory operations
    public Product getProductBySku(String sku) {
        for (Warehouse warehouse : warehouses) {
            Product product = warehouse.getProductBySku(sku);
            if (product != null) {
                return product;
            }
        }
        return null;
    }

    // Check stock levels and apply replenishment strategy if needed
    public void checkAndReplenish(String sku) {
        Product product = getProductBySku(sku);
        if (product != null) {
            // If product is below threshold
            if (product.getQuantity() < product.getThreshold()) {
                // Apply current replenishment strategy
                if (replenishmentStrategy != null) {
                    replenishmentStrategy.replenish(product);
                }
            }
        }
    }

    // Global inventory check
    public void performInventoryCheck() {
        for (Warehouse warehouse : warehouses) {
            for (Product product : warehouse.getAllProducts()) {
                if (product.getQuantity() < product.getThreshold()) {
                    if (replenishmentStrategy != null)  replenishmentStrategy.replenish(product);
                }
            }
        }
    }
}
