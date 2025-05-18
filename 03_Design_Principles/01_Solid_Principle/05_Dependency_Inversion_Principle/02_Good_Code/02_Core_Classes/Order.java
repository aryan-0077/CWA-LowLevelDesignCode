package Dependency_Inversion_Principle.Good_Code.Core_Classes;

public class Order {
    private int id;
    private Product product;

    public int getId(){
        return id;
    }

    public Product getProduct() {
        return product;
    }
}
