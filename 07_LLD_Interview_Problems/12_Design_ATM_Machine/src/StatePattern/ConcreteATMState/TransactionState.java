package StatePattern.ConcreteATMState;

import StatePattern.ATMContext.ATMMachineContext;
import StatePattern.ATMState;

public class TransactionState implements ATMState {
    public TransactionState() {
        System.out.println("ATM is in Transaction State");
    }

    @Override
    public String getStateName() {
        return "TransactionState";
    }

    @Override
    public ATMState next(ATMMachineContext context) {
        if (context.getCurrentCard() == null) {
            return context.getStateFactory().createIdleState();
        }

        // After transaction completion, go back to select operation
        return context.getStateFactory().createSelectOperationState();
    }
}
