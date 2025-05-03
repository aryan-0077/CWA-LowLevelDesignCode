package UtilityClasses;

public class Card {
    private String cardNumber;
    private int pin;
    private String accountNumber;
    // Constructor
    public Card(String cardNumber, int pin, String accountNumber) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.accountNumber = accountNumber;
    }
    // Getters and Setters
    public String getCardNumber() {
        return cardNumber;
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
