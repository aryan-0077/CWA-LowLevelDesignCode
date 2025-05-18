package Ugly_Code;

public class Main {
    public static void main(String[] args) {
        // Interviewer's requirement: Only DebitCard and CreditCard payments
        PaymentProcessor processor = new PaymentProcessor("CreditCard");
        processor.processPayment(100); // Output: Processing payment of $100 via Credit Card.
        PaymentProcessor invalidProcessor = new PaymentProcessor("PayPal");
        invalidProcessor.processPayment(50); // Output: Processing payment of $50 via PayPal. (Not required!)
    }
}

/*

Output :
Processing payment of $100.0 via Credit Card.
Processing payment of $50.0 via PayPal.


*/