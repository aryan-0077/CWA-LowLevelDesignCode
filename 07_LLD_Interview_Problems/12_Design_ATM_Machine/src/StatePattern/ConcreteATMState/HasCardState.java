package StatePattern.ConcreteATMState;

import StatePattern.ATMContext.ATMMachineContext;
import StatePattern.ATMState;

public class HasCardState implements ATMState {
    public HasCardState() {
        System.out.println("ATM is in Has Card State - Please enter your PIN");
    }

    @Override
    public String getStateName() {
        return "HasCardState";
    }


    @Override
    public ATMState next(ATMMachineContext context) {
        if (context.getCurrentCard() == null) {
            return context.getStateFactory().createIdleState();
        }
        if (context.getCurrentAccount() != null) {
            return context.getStateFactory().createSelectOperationState();
        }
        return this;
    }
}
