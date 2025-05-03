package UtilityClasses;

import java.util.List;
import java.util.Map;

public class Expense {
    private String id;                     // id of the expense
    private String description;           // Description of the expense
    private double amount;                // Total amount of the expense
    private User payer;                   // User who paid the expense
    private List<User> participants;      // List of users sharing the expense
    private Map<User, Double> shares;     // Split amounts owed by each participant

    // Constructor to initialize Expense attributes
    public Expense(String id, String description, double amount, User payer, List<User> participants, Map<User, Double> shares) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.payer = payer;
        this.participants = participants;
        this.shares = shares;
    }

    // Getters for the expense attributes
    public String getId() { return id; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public User getPayer() { return payer; }
    public List<User> getParticipants() { return participants; }
    public Map<User, Double> getShares() { return shares; }
}
