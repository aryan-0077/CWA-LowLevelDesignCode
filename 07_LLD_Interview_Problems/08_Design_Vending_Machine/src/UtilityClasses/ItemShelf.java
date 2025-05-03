package UtilityClasses;

import java.util.ArrayList;
import java.util.List;

// Class representing a slot in the vending machine that holds multiple items
public class ItemShelf {
    // Code to identify the slot
    private int code;
    // List of items stored in the slot
    private List<Item> items;
    // Flag to indicate if the shelf is sold out
    private boolean isSoldOut;

    // Constructor to initialize the item shelf
    public ItemShelf(int code) {
        this.code = code;
        this.items = new ArrayList<>(); // Initialize the list of items
        this.isSoldOut = false;
    }

    // Getters and Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean checkIsSoldOut() {
        return isSoldOut;
    }

    public void setIsSoldOut(boolean isSoldOut) {
        this.isSoldOut = isSoldOut;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        if(isSoldOut) setIsSoldOut(false); // Update the sold-out status when items are set
    }

    // Add an item to the shelf
    public void addItem(Item item) {
        items.add(item);
        if(isSoldOut) setIsSoldOut(false); // Update sold-out status after adding an item
    }

    // Remove an item from the shelf
    public void removeItem(Item item) {
        items.remove(item);
        if(items.isEmpty()) setIsSoldOut(true); // Update sold-out status after removing an item
    }
}