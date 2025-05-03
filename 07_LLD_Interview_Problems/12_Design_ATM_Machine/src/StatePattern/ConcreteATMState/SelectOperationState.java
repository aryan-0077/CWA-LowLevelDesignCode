package StatePattern.ConcreteATMState;

import StatePattern.ATMContext.ATMMachineContext;
import StatePattern.ATMState;

public class SelectOperationState implements ATMState {
    public SelectOperationState() {
        System.out.println("ATM is in Select Operation State - Please select an operation");
        System.out.println("1. Withdraw Cash");
        System.out.println("2. Check Balance");
    }
    @Override
    public String getStateName() {
        return "SelectOperationState";
    }
    @Override
    public ATMState next(ATMMachineContext context) {
        if (context.getCurrentCard() == null) {
            return context.getStateFactory().createIdleState();
        }

        if (context.getSelectedOperation() != null) {
            return context.getStateFactory().createTransactionState();
        }
        return this;
    }
}
