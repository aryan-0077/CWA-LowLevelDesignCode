package ProductsFactory;

import CommonEnum.ProductCategory;

public class Product {
    private String sku;
    private String name;
    private double price;
    private int quantity;
    private int threshold;
    private ProductCategory productCategory;

    // Getters and setters
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreshold() {
        return quantity;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}