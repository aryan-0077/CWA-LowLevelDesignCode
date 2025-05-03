package UtilityClasses;

// Class representing the inventory of the vending machine
public class Inventory {
    // Array to hold item shelves in the inventory
    ItemShelf[] inventory = null;

    // Constructor for Inventory
    public Inventory(int itemCount) {
        inventory = new ItemShelf[itemCount]; // Initialize the array with the given item count
        initialEmptyInventory(); // Initialize the inventory with empty shelves
    }

    // Getter for the inventory array
    public ItemShelf[] getInventory() {
        return inventory;
    }

    // Setter for the inventory array
    public void setInventory(ItemShelf[] inventory) {
        this.inventory = inventory;
    }

    // Method to initialize the inventory with empty shelves
    public void initialEmptyInventory() {
        int startCode = 101; // Starting code for item shelves
        for (int i = 0; i < inventory.length; i++) {
            ItemShelf space = new ItemShelf(startCode); // Create a new item shelf with a code
            inventory[i] = space; // Add the shelf to the inventory
            startCode++; // Increment the code for the next shelf
        }
    }

    // Method to add an item to the inventory at a specific code number
    public void addItem(Item item, int codeNumber) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == codeNumber) {
                // Add the item to the shelf
                itemShelf.addItem(item);
                return;
            }
        }
        throw new Exception("Invalid Code");
    }

    // Method to get and remove an item from the inventory by its code number
    public Item getItem(int codeNumber) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == codeNumber) {
                if (itemShelf.checkIsSoldOut()) {
                    throw new Exception("Item already sold out");
                } else {
                    // Get and remove the first item from the shelf
                    Item item = itemShelf.getItems().get(0); // Get the first item
                    return item;
                }
            }
        }
        throw new Exception("Invalid Code");
    }

    // Method to update an item shelf as sold out by its code number
    public void updateSoldOutItem(int codeNumber) {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == codeNumber) {
                if (itemShelf.getItems().isEmpty())
                    itemShelf.setIsSoldOut(true); // Mark the shelf as sold out
            }
        }
    }

    // Method to remove a specific item from the inventory by its code number
    public void removeItem(int codeNumber) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == codeNumber) {
                itemShelf.removeItem(
                        itemShelf.getItems().get(0)); // Remove the specific item from the shelf
                return;
            }
        }
        throw new Exception("Invalid Code");
    }

    public boolean hasItems() {
        for(ItemShelf itemShelf : inventory){
            if(!itemShelf.checkIsSoldOut()) return true;
        }
        return false;
    }
}
