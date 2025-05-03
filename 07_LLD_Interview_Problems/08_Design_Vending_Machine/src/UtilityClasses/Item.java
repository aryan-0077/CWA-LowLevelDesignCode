package UtilityClasses;

import CommonEnums.ItemType;

// Class representing an item in the vending machine
public class Item {
    // Type of the item (e.g., COKE, PEPSI)
    private ItemType type;

    // Price of the item
    private int price;
    // Getters and Setters
    public ItemType getType() {
        return type;
    }
    public void setType(ItemType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
