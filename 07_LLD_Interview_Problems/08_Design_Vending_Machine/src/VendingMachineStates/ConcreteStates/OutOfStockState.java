package VendingMachineStates.ConcreteStates;

import VendingMachineStates.VendingMachineContext;
import VendingMachineStates.VendingMachineState;

public class OutOfStockState implements VendingMachineState {
    public OutOfStockState() {
        System.out.println("Vending machine is now in Out of Stock State");
    }

    @Override
    public String getStateName() {
        return "OutOfStockState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context) {
        // If inventory has items again, return to idle state
        if (context.getInventory().hasItems()) {
            return new IdleState();
        }

        // Otherwise, remain in out of stock state
        return this;
    }
}
