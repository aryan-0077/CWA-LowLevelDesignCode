package StatePattern;

import StatePattern.ATMContext.ATMMachineContext;

public interface ATMState {
    // Get the name of the current state
    String getStateName();

    // Method to handle state transitions
    ATMState next(ATMMachineContext context);
}
