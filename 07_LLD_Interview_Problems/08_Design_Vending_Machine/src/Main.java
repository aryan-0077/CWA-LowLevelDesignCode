import CommonEnums.Coin;
import CommonEnums.ItemType;
import UtilityClasses.Item;
import UtilityClasses.ItemShelf;
import VendingMachineStates.VendingMachineContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a new vending machine context
        VendingMachineContext vendingMachine = new VendingMachineContext();
        try {
            System.out.println("|");
            System.out.println("Filling up the inventory");
            System.out.println("|");
            fillUpInventory(vendingMachine); // Fill up the inventory with items
            displayInventory(vendingMachine); // Display the current inventory
            System.out.println("|");
            System.out.println("Inserting coins");
            System.out.println("|");
            // Insert coins using the context methods
            vendingMachine.clickOnInsertCoinButton(Coin.TEN_RUPEES);
            vendingMachine.clickOnInsertCoinButton(Coin.FIVE_RUPEES);
            System.out.println("|");
            System.out.println("Clicking on ProductSelectionButton");
            System.out.println("|");
            // Start product selection and choose a product
            vendingMachine.clickOnStartProductSelectionButton(102);
            // Display the updated inventory
            displayInventory(vendingMachine);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            displayInventory(vendingMachine);
        }
    }

    // Method to fill up the inventory of the vending machine
    private static void fillUpInventory(VendingMachineContext vendingMachine) {
        for (int i = 0; i < 10; i++) {
            Item newItem = new Item();
            int codeNumber = 101 + i; // Shelf code
            // Set item type and price based on the index range
            if (i >= 0 && i < 3) {
                newItem.setType(ItemType.COKE);
                newItem.setPrice(12);
            } else if (i >= 3 && i < 5) {
                newItem.setType(ItemType.PEPSI);
                newItem.setPrice(9);
            } else if (i >= 5 && i < 7) {
                newItem.setType(ItemType.JUICE);
                newItem.setPrice(13);
            } else if (i >= 7 && i < 10) {
                newItem.setType(ItemType.SODA);
                newItem.setPrice(7);
            }
            // Update the inventory with multiple same items per shelf
            for (int j = 0; j < 5; j++) {
                // Add 5 items to each shelf
                vendingMachine.updateInventory(newItem, codeNumber);
            }
        }
    }

    // Method to display the current inventory of the vending machine
    private static void displayInventory(VendingMachineContext vendingMachine) {
        ItemShelf[] slots = vendingMachine.getInventory().getInventory();
        for (ItemShelf slot : slots) {
            List<Item> items = slot.getItems(); // Get the list of items in the shelf
            if (!items.isEmpty()) {
                System.out.println("CodeNumber: " + slot.getCode() + " Items: ");
                for (Item item : items) { // Display all items in the shelf
                    System.out.println(
                            "    - Item: " + item.getType().name() + ", Price: " + item.getPrice());
                }
                System.out.println("SoldOut: " + slot.checkIsSoldOut());
            } else {
                // Display empty shelf information
                System.out.println("CodeNumber: " + slot.getCode() + " Items: EMPTY"
                        + " SoldOut: " + slot.checkIsSoldOut());
            }
        }
    }
}

/*

Output : 

Vending machine is now in Idle State
Initialized: IdleState
|
Filling up the inventory
|
Added COKE to slot 101
Added COKE to slot 101
Added COKE to slot 101
Added COKE to slot 101
Added COKE to slot 101
Added COKE to slot 102
Added COKE to slot 102
Added COKE to slot 102
Added COKE to slot 102
Added COKE to slot 102
Added COKE to slot 103
Added COKE to slot 103
Added COKE to slot 103
Added COKE to slot 103
Added COKE to slot 103
Added PEPSI to slot 104
Added PEPSI to slot 104
Added PEPSI to slot 104
Added PEPSI to slot 104
Added PEPSI to slot 104
Added PEPSI to slot 105
Added PEPSI to slot 105
Added PEPSI to slot 105
Added PEPSI to slot 105
Added PEPSI to slot 105
Added JUICE to slot 106
Added JUICE to slot 106
Added JUICE to slot 106
Added JUICE to slot 106
Added JUICE to slot 106
Added JUICE to slot 107
Added JUICE to slot 107
Added JUICE to slot 107
Added JUICE to slot 107
Added JUICE to slot 107
Added SODA to slot 108
Added SODA to slot 108
Added SODA to slot 108
Added SODA to slot 108
Added SODA to slot 108
Added SODA to slot 109
Added SODA to slot 109
Added SODA to slot 109
Added SODA to slot 109
Added SODA to slot 109
Added SODA to slot 110
Added SODA to slot 110
Added SODA to slot 110
Added SODA to slot 110
Added SODA to slot 110
CodeNumber: 101 Items:
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
SoldOut: false
CodeNumber: 102 Items:
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
SoldOut: false
CodeNumber: 103 Items:
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
SoldOut: false
CodeNumber: 104 Items:
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
SoldOut: false
CodeNumber: 105 Items:
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
SoldOut: false
CodeNumber: 106 Items:
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
SoldOut: false
CodeNumber: 107 Items:
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
SoldOut: false
CodeNumber: 108 Items:
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
SoldOut: false
CodeNumber: 109 Items:
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
SoldOut: false
CodeNumber: 110 Items:
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
SoldOut: false
|
Inserting coins
|
Inserted TEN_RUPEES worth 10
Vending machine is now in HasMoney State
Current state: HasMoneyState
Inserted FIVE_RUPEES worth 5
Vending machine is now in Selection State
Current state: SelectionState
|
Clicking on ProductSelectionButton
|
Product selection button can only be clicked in HasMoney state
CodeNumber: 101 Items:
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
SoldOut: false
CodeNumber: 102 Items:
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
SoldOut: false
CodeNumber: 103 Items:
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
    - Item: COKE, Price: 12
SoldOut: false
CodeNumber: 104 Items:
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
SoldOut: false
CodeNumber: 105 Items:
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
    - Item: PEPSI, Price: 9
SoldOut: false
CodeNumber: 106 Items:
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
SoldOut: false
CodeNumber: 107 Items:
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
    - Item: JUICE, Price: 13
SoldOut: false
CodeNumber: 108 Items:
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
SoldOut: false
CodeNumber: 109 Items:
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
SoldOut: false
CodeNumber: 110 Items:
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
    - Item: SODA, Price: 7
SoldOut: false

*/