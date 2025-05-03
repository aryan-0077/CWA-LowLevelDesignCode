package UtilityClasses;

public class Transaction {
    private User from;        // User who owes the money
    private User to;          // User who is owed the money
    private double amount;    // Transaction amount

    // Constructor to initialize Transaction attributes
    public Transaction(User from, User to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    // Getters for the transaction attributes
    public User getFrom() { return from; }
    public User getTo() { return to; }
    public double getAmount() { return amount; }
}
