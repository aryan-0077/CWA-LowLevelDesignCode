package StatePattern.ConcreteATMState;

import StatePattern.ATMContext.ATMMachineContext;
import StatePattern.ATMState;

public class IdleState implements ATMState {
    public IdleState() {
        System.out.println("ATM is in Idle State - Please insert your card");
    }

    @Override
    public String getStateName() {
        return "IdleState";
    }

    @Override
    public ATMState next(ATMMachineContext context) {
        if (context.getCurrentCard() != null) {
            return context.getStateFactory().createHasCardState();
        }
        return this;
    }
}
