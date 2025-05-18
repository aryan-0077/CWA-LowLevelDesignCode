package Good_Code;

public class Main {
    public static void main(String[] args) {
        // Interviewer's requirement: Only DebitCard and CreditCard payments
        PaymentProcessor creditCardProcessor = new PaymentProcessor("CreditCard");
        creditCardProcessor.processPayment(100); // Output: Processing payment of $100 via Credit Card.


        PaymentProcessor debitCardProcessor = new PaymentProcessor("DebitCard");
        debitCardProcessor.processPayment(200); // Output: Processing payment of $200 via Debit Card.


        // Unsupported method
        PaymentProcessor unsupportedProcessor = new PaymentProcessor("PayPal");
        unsupportedProcessor.processPayment(50); // Output: Payment method not supported.
    }
}

/*

Output :
 Processing payment of $100.0 via Credit Card.
Processing payment of $200.0 via Debit Card.
Payment method not supported.


*/