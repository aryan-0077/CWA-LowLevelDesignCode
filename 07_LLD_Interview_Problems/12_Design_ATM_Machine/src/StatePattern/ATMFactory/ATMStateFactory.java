package StatePattern.ATMFactory;

import StatePattern.ATMState;
import StatePattern.ConcreteATMState.HasCardState;
import StatePattern.ConcreteATMState.IdleState;
import StatePattern.ConcreteATMState.SelectOperationState;
import StatePattern.ConcreteATMState.TransactionState;

public class ATMStateFactory {
    // ATM State Factory (Singleton)
        private static ATMStateFactory instance = null;

        private ATMStateFactory() {}

        public static ATMStateFactory getInstance() {
            if (instance == null) {
                instance = new ATMStateFactory();
            }
            return instance;
        }

        public ATMState createIdleState() {
            return (ATMState) new IdleState();
        }

        public ATMState createHasCardState() {
            return (ATMState) new HasCardState();
        }

        public ATMState createSelectOperationState() {
            return (ATMState) new SelectOperationState();
        }

        public ATMState createTransactionState() {
            return (ATMState) new TransactionState();
        }
}
