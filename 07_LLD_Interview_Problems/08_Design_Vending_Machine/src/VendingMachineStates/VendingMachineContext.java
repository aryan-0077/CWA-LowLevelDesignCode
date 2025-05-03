package VendingMachineStates;

import CommonEnums.Coin;
import UtilityClasses.*;
import VendingMachineStates.ConcreteStates.DispenseState;
import VendingMachineStates.ConcreteStates.HasMoneyState;
import VendingMachineStates.ConcreteStates.IdleState;
import VendingMachineStates.ConcreteStates.SelectionState;

import java.util.ArrayList;
import java.util.List;

// Context class that maintains state and handles transitions in the vending machine
public class VendingMachineContext {
    private VendingMachineState currentState; // Current state of the vending machine
    private Inventory inventory; // Inventory to store items
    private List<Coin> coinList; // List to keep track of inserted coins
    private int selectedItemCode; // Code of the selected item


    // Constructor to initialize the vending machine with an idle state
    public VendingMachineContext() {
        inventory = new Inventory(10); // Initialize inventory with 10 slots
        coinList = new ArrayList<>(); // Initialize the coin list
        currentState = new IdleState(); // Set initial state to idle
        System.out.println("Initialized: " + currentState.getStateName());
    }

    // Gets the current state of the vending machine
    public VendingMachineState getCurrentState() {
        return currentState;
    }

    // Advances the vending machine to the next state
    public void advanceState() {
        VendingMachineState nextState = currentState.next(this); // Get the next state
        currentState = nextState; // Transition to the next state
        System.out.println("Current state: " + currentState.getStateName());
    }

    // Handles the insertion of a coin
    public void clickOnInsertCoinButton(Coin coin) {
        if (currentState instanceof IdleState || currentState instanceof HasMoneyState) {
            System.out.println("Inserted " + coin.name() + " worth " + coin.value);
            coinList.add(coin); // Add the coin to the list
            advanceState(); // Move to the next state
        } else {
            System.out.println("Cannot insert coin in " + currentState.getStateName());
        }
    }

    // Handles the product selection process
    public void clickOnStartProductSelectionButton(int codeNumber) {
        if (currentState instanceof HasMoneyState) {
            advanceState(); // Move to selection state
            selectProduct(codeNumber); // Select the product
        } else {
            System.out.println("Product selection button can only be clicked in HasMoney state");
        }
    }

    // Selects a product based on its code
    public void selectProduct(int codeNumber) {
        if (currentState instanceof SelectionState) {
            try {
                Item item = inventory.getItem(codeNumber); // Fetch the item from inventory

                int balance = getBalance(); // Calculate the total balance
                if (balance < item.getPrice()) { // Check for sufficient funds
                    System.out.println(
                            "Insufficient amount. Product price: " + item.getPrice() + ", paid: " + balance);
                    return;
                }
                setSelectedItemCode(codeNumber); // Set the selected item code
                advanceState(); // Move to dispense state
                dispenseItem(codeNumber); // Dispense the item

                if (balance >= item.getPrice()) { // Return change if applicable
                    int change = balance - item.getPrice();
                    System.out.println("Returning change: " + change);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Products can only be selected in Selection state");
        }
    }

    // Dispenses the selected item
    public void dispenseItem(int codeNumber) {
        if (currentState instanceof DispenseState) {
            try {
                Item item = inventory.getItem(codeNumber);
                System.out.println("Dispensing: " + item.getType());
                inventory.removeItem(codeNumber); // Remove item from inventory
                // Update inventory
                inventory.updateSoldOutItem(codeNumber);
                // Reset machine state
                resetBalance();
                resetSelection();
                advanceState(); // Move to the next state
            } catch (Exception e) {
                System.out.println("Failed to Dispense the Product with code : " + codeNumber);
            }
        } else {
            System.out.println("System cannot dispense in : " + currentState);
        }
    }

    // Updates the inventory by adding a new item
    public void updateInventory(Item item, int codeNumber) {
        if (currentState instanceof IdleState) { // Only update inventory in Idle state
            try {
                inventory.addItem(item, codeNumber); // Add the item to inventory
                System.out.println("Added " + item.getType() + " to slot " + codeNumber);
            } catch (Exception e) {
                System.out.println("Error updating inventory: " + e.getMessage());
            }
        } else {
            System.out.println("Inventory can only be updated in Idle state");
        }
    }

    // Getters and setters for context properties
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

    public void setCoinList(List<Coin> coinList) {
        this.coinList = coinList;
    }

    public int getSelectedItemCode() {
        return selectedItemCode;
    }

    public void setSelectedItemCode(int codeNumber) {
        this.selectedItemCode = codeNumber;
    }

    // Resets the product selection
    public void resetSelection() {
        this.selectedItemCode = 0;
    }

    // Calculates the total balance from inserted coins
    public int getBalance() {
        int balance = 0;
        for (Coin coin : coinList) {
            balance += coin.value; // Sum up the coin values
        }
        return balance;
    }

    // Resets the balance by clearing all coins
    public void resetBalance() {
        coinList.clear();
    }
}