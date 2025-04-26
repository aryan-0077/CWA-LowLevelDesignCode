package ProductReplenishmentStrategies.ConcreteReplenishmentStrategies;

import ProductReplenishmentStrategies.ReplenishmentStrategy;
import ProductsFactory.Product;

public class JustInTimeStrategy implements ReplenishmentStrategy {
    @Override
    public void replenish(Product product) {
        // Implement Just-In-Time replenishment logic
        System.out.println("Applying Just-In-Time replenishment for " + product.getName());
        // Calculate optimal order quantity based on demand rate
    }
}
