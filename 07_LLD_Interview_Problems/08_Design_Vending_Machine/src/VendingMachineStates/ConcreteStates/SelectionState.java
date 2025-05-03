package VendingMachineStates.ConcreteStates;

import VendingMachineStates.VendingMachineContext;
import VendingMachineStates.VendingMachineState;

public class SelectionState implements VendingMachineState {
    public SelectionState() {
        System.out.println("Vending machine is now in Selection State");
    }

    @Override
    public String getStateName() {
        return "SelectionState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context) {
        // If inventory has no items, transition to OutOfStock
        if (!context.getInventory().hasItems()) {
            return new OutOfStockState();
        }
        // If no money left, go back to idle
        if (context.getCoinList().isEmpty()) {
            return new IdleState();
        }
        // If an item has been selected, transition to dispense state
        if (context.getSelectedItemCode() > 0) {
            return new DispenseState();
        }
        // Otherwise, remain in Selection state
        return this;
    }
}
