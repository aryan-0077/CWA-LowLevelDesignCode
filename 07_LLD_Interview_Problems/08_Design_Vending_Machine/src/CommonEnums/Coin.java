package CommonEnums;

// Enum representing different types of Indian coins in the vending machine
public enum Coin {
    ONE_RUPEE(1),
    TWO_RUPEES(2),
    FIVE_RUPEES(5),
    TEN_RUPEES(10);
    // Value of the coin in Indian rupees
    public int value;

    // Constructor to initialize the coin's value
    Coin(int value) {
        this.value = value;
    }
}