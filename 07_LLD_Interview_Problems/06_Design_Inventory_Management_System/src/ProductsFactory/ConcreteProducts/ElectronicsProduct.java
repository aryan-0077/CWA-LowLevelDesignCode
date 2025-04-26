package ProductsFactory.ConcreteProducts;

import CommonEnum.ProductCategory;
import ProductsFactory.Product;

public class ElectronicsProduct extends Product {
    private String brand;
    private int warrantyPeriod; // in months

    public ElectronicsProduct(String sku, String name, double price, int quantity, int threshold) {
        super();
        setSku(sku);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setCategory(ProductCategory.ELECTRONICS);
        setThreshold(threshold);
    }

    // Getters and setters for electronics-specific attributes
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}