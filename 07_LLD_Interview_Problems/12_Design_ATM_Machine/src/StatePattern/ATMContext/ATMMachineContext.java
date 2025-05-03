package StatePattern.ATMContext;

import CommonEnum.CashType;
import CommonEnum.TransactionType;
import StatePattern.ATMFactory.ATMStateFactory;
import StatePattern.ATMState;
import StatePattern.ConcreteATMState.HasCardState;
import StatePattern.ConcreteATMState.IdleState;
import StatePattern.ConcreteATMState.SelectOperationState;
import StatePattern.ConcreteATMState.TransactionState;
import UtilityClasses.ATMInventory;
import UtilityClasses.Account;
import UtilityClasses.Card;

import java.util.HashMap;
import java.util.Map;

public class ATMMachineContext {
    private ATMState currentState;
    private Card currentCard;
    private Account currentAccount;
    private ATMInventory atmInventory;
    private Map<String, Account> accounts; // Simplified account storage
    private ATMStateFactory stateFactory;
    private TransactionType selectedOperation;

    // Constructor
    public ATMMachineContext() {
        this.stateFactory = ATMStateFactory.getInstance();
        this.currentState = stateFactory.createIdleState();
        this.atmInventory = new ATMInventory();
        this.accounts = new HashMap<>();
        System.out.println("ATM initialized in: " + currentState.getStateName());
    }

    // Method to advance to the next state
    public void advanceState() {
        ATMState nextState = currentState.next(this);
        currentState = nextState;
        System.out.println("Current state: " + currentState.getStateName());
    }

    // Card insertion operation
    public void insertCard(Card card) {
        if (currentState instanceof IdleState) {
            System.out.println("Card inserted");
            this.currentCard = card;
            advanceState();
        } else {
            System.out.println(
                    "Cannot insert card in " + currentState.getStateName());
        }
    }

    // PIN authentication operation
    public void enterPin(int pin) {
        if (currentState instanceof HasCardState) {
            if (currentCard.validatePin(pin)) {
                System.out.println("PIN authenticated successfully");
                currentAccount = accounts.get(currentCard.getAccountNumber());
                advanceState();
            } else {
                System.out.println("Invalid PIN. Please try again");
                // Could implement PIN retry logic here
            }
        } else {
            System.out.println("Cannot enter PIN in " + currentState.getStateName());
        }
    }

    // Select operation (withdrawal, balance check, etc.)
    public void selectOperation(TransactionType transactionType) {
        if (currentState instanceof SelectOperationState) {
            System.out.println("Selected operation: " + transactionType);
            this.selectedOperation = transactionType;
            advanceState();
        } else {
            System.out.println(
                    "Cannot select operation in " + currentState.getStateName());
        }
    }

    // Perform the selected transaction
    public void performTransaction(double amount) {
        if (currentState instanceof TransactionState) {
            try {
                if (selectedOperation == TransactionType.WITHDRAW_CASH) {
                    performWithdrawal(amount);
                } else if (selectedOperation == TransactionType.CHECK_BALANCE) {
                    checkBalance();
                }
                // Ask if user wants another transaction
                advanceState();
            } catch (Exception e) {
                System.out.println("Transaction failed: " + e.getMessage());
                // Go back to select operation state
                currentState = stateFactory.createSelectOperationState();
            }
        } else {
            System.out.println(
                    "Cannot perform transaction in " + currentState.getStateName());
        }
    }

    // Return card to user
    public void returnCard() {
        if (currentState instanceof HasCardState
                || currentState instanceof SelectOperationState
                || currentState instanceof TransactionState) {
            System.out.println("Card returned to customer");
            resetATM();
        } else {
            System.out.println("No card to return in " + currentState.getStateName());
        }
    }

    // Cancel current transaction
    public void cancelTransaction() {
        if (currentState instanceof TransactionState
                || currentState instanceof TransactionState) {
            System.out.println("Transaction cancelled");
            returnCard();
        } else {
            System.out.println(
                    "No transaction to cancel in " + currentState.getStateName());
        }
    }

    // Helper method to perform withdrawal
    private void performWithdrawal(double amount) throws Exception {
        // Check if user has sufficient balance
        if (!currentAccount.withdraw(amount)) {
            throw new Exception("Insufficient funds in account");
        }
        // Check if ATM has sufficient cash
        if (!atmInventory.hasSufficientCash((int) amount)) {
            // Rollback the account withdrawal
            currentAccount.deposit(amount);
            throw new Exception("Insufficient cash in ATM");
        }
        Map<CashType, Integer> dispensedCash =
                atmInventory.dispenseCash((int) amount);
        if (dispensedCash == null) {
            // Rollback the account withdrawal
            currentAccount.deposit(amount);
            throw new Exception("Unable to dispense exact amount");
        }
        System.out.println("Transaction successful. Please collect your cash:");
        for (Map.Entry<CashType, Integer> entry : dispensedCash.entrySet()) {
            System.out.println(entry.getValue() + " x $" + entry.getKey().value);
        }
    }

    // Helper method to check balance
    private void checkBalance() {
        System.out.println(
                "Your current balance is: $" + currentAccount.getBalance());
    }

    // Reset ATM state
    private void resetATM() {
        this.currentCard = null;
        this.currentAccount = null;
        this.selectedOperation = null;
        this.currentState = stateFactory.createIdleState();
    }

    // Getters and setters
    public ATMState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ATMState state) {
        this.currentState = state;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public ATMInventory getATMInventory() {
        return atmInventory;
    }

    public TransactionType getSelectedOperation() {
        return selectedOperation;
    }

    public ATMStateFactory getStateFactory() {
        return stateFactory;
    }

    // Add an account to the ATM (for demo purposes)
    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    // Get account by number
    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}
