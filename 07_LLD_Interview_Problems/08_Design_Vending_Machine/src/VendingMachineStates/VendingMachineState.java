package VendingMachineStates;

// Interface defining the common methods for all states
public interface VendingMachineState {
    // Get the name of the current state
    String getStateName();
    // Method to handle state transitions
    VendingMachineState next(VendingMachineContext context);
}