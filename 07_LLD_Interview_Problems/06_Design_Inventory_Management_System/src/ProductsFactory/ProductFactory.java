package ProductsFactory;

import CommonEnum.ProductCategory;
import ProductsFactory.ConcreteProducts.ClothingProduct;
import ProductsFactory.ConcreteProducts.ElectronicsProduct;
import ProductsFactory.ConcreteProducts.GroceryProduct;

// Product Factory class implementing Factory Pattern
public class ProductFactory {
    public Product createProduct(ProductCategory category, String sku, String name, double price, int quantity, int threshold) {
        switch (category) {
            case ELECTRONICS:
                return new ElectronicsProduct(sku, name, price, quantity,threshold);
            case CLOTHING:
                return new ClothingProduct(sku, name, price, quantity,threshold);
            case GROCERY:
                return new GroceryProduct(sku, name, price, quantity,threshold);
            default:
                throw new IllegalArgumentException(
                        "Unsupported product category: " + category);
        }
    }
}
