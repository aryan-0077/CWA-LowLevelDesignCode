package Advantages.Ease_Of_Maintenance;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    // Centralized logic for deposit and withdrawal
    public void updateBalance(double amount, boolean isDeposit) {
        if (isDeposit) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Invalid transaction.");
        }
    }
}