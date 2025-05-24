package Realization;

public class RealizationDemo {
    public static void main(String[] args) {
        Payment payment1 = new CreditCardPayment();
        Payment payment2 = new CashPayment();
        payment1.pay();
        payment2.pay();
    }
}

/*

Output :
Paid using Credit Card.
Paid using Cash.

*/