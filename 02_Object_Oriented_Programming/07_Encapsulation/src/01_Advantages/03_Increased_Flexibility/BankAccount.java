package Advantages.Increased_Flexibility;

public class BankAccount {
    private double balance;
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    public double getBalance() {
        return balance;
    }

    // Internal implementation can be updated (e.g., adding transaction fee)
    public void withdraw(double amount) {
        double transactionFee = 2.00; // New feature
        if (amount > 0 && (amount + transactionFee) <= balance) {
            balance -= (amount + transactionFee);
            System.out.println("Withdrawn: " + amount + ", Fee: " + transactionFee);
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }
}
