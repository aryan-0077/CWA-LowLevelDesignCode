package ProductsFactory.ConcreteProducts;

import CommonEnum.ProductCategory;
import ProductsFactory.Product;

public class ClothingProduct extends Product {
    private String size;
    private String color;

    public ClothingProduct(String sku, String name, double price, int quantity,int threshold) {
        super();
        setSku(sku);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setCategory(ProductCategory.CLOTHING);
        setThreshold(threshold);
    }

    // Getters and setters for clothing-specific attributes
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
