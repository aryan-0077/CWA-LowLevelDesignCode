import CommonEnum.TransactionType;
import StatePattern.ATMContext.ATMMachineContext;
import UtilityClasses.Account;
import UtilityClasses.Card;

public class Main {
    public static void main(String[] args) {
        // Create and initialize ATM
        ATMMachineContext atm = new ATMMachineContext();

        // Add sample accounts
        atm.addAccount(new Account("123456", 1000.0));
        atm.addAccount(new Account("654321", 500.0));

        try {
            // Sample workflow
            System.out.println("=== Starting ATM Demo ===");

            // Insert card
            atm.insertCard(new Card("123456", 1234, "654321"));

            // Enter PIN
            atm.enterPin(1234);

            // Select operation
            atm.selectOperation(TransactionType.WITHDRAW_CASH);

            // Perform transaction
            atm.performTransaction(100.0);

            // Select another operation
            atm.selectOperation(TransactionType.CHECK_BALANCE);

            // Perform balance check
            atm.performTransaction(0.0);

            // Return card
            atm.returnCard();

            System.out.println("=== ATM Demo Completed ===");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/*

Output :

ATM is in Idle State - Please insert your card
ATM initialized in: IdleState
=== Starting ATM Demo ===
Card inserted
ATM is in Has Card State - Please enter your PIN
Current state: HasCardState
PIN authenticated successfully
ATM is in Select Operation State - Please select an operation
1. Withdraw Cash
2. Check Balance
Current state: SelectOperationState
Selected operation: WITHDRAW_CASH
ATM is in Transaction State
Current state: TransactionState
Transaction successful. Please collect your cash:
1 x $100
ATM is in Select Operation State - Please select an operation
1. Withdraw Cash
2. Check Balance
Current state: SelectOperationState
Selected operation: CHECK_BALANCE
ATM is in Transaction State
Current state: TransactionState
Your current balance is: $400.0
ATM is in Select Operation State - Please select an operation
1. Withdraw Cash
2. Check Balance
Current state: SelectOperationState
Card returned to customer
ATM is in Idle State - Please insert your card
=== ATM Demo Completed ===

Process finished with exit code 0


*/