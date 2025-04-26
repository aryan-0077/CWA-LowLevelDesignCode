package UtilityClasses;

import ProductsFactory.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private int id;
    private String name;
    private String location;
    private Map<String, Product> products; // SKU -> Product

    public Warehouse(String name){
        this.name = name;
        this.products = new HashMap<>();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Add a product to the warehouse
    public void addProduct(Product product, int quantity) {
        String sku = product.getSku();
        if (products.containsKey(sku)) {
            // Product exists, update quantity
            Product existingProduct = products.get(sku);
            existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
        } else {
            // New product, add to inventory
            product.setQuantity(quantity);
        }
        products.put(sku, product);
        System.out.println(quantity + " units of " + product.getName()
                + " (SKU: " + sku + ") added to " + name
                + ". New quantity: " + getAvailableQuantity(sku));
    }

    // Remove a product from the warehouse
    public boolean removeProduct(String sku, int quantity) {
        if (products.containsKey(sku)) {
            Product product = products.get(sku);
            int currentQuantity = product.getQuantity();
            if (currentQuantity >= quantity) {
                // Sufficient inventory to remove
                product.setQuantity(currentQuantity - quantity);
                System.out.println(quantity + " units of " + product.getName()
                        + " (SKU: " + sku + ") removed from " + name
                        + ". Remaining quantity: " + product.getQuantity());
                // If quantity becomes zero, consider removing the product entirely
                if (product.getQuantity() == 0) {
                    // Remove products with zero quantity
                    products.remove(sku);
                    System.out.println("Product " + product.getName()
                            + " removed from inventory as quantity is now zero.");
                }
                return true;
            } else {
                System.out.println("Error: Insufficient inventory. Requested: "
                        + quantity + ", Available: " + currentQuantity);
                return false;
            }
        } else {
            System.out.println(
                    "Error: Product with SKU " + sku + " not found in " + name);
            return false;
        }
    }

    // Get available quantity of a product
    public int getAvailableQuantity(String sku) {
        if (products.containsKey(sku)) {
            return products.get(sku).getQuantity();
        }
        return 0; // Product not found
    }

    // Get a product by SKU
    public Product getProductBySku(String sku) {
        return products.get(sku);
    }

    // Get all products in this warehouse
    public Collection<Product> getAllProducts() {
        return products.values();
    }
}