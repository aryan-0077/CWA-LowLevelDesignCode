package Good_Code;

import Good_Code.Concrete_Payment_Methods.*;

public class Main {
    public static void main(String[] args) {
        // Create strategy instances for each payment type
        PaymentStrategy creditCard = new CreditCardPayment();
        PaymentStrategy payPal = new PayPalPayment();
        PaymentStrategy crypto = new CryptoPayment();
        PaymentStrategy stripe = new StripePayment();
        // Use the Strategy Pattern to process payments
        PaymentProcessor processor = new PaymentProcessor(creditCard); // Initially using CreditCardPayment
        processor.processPayment(); // Processing credit card payment...
        // Dynamically change the payment strategy to PayPal
        processor.setPaymentStrategy(payPal);
        processor.processPayment(); // Processing PayPal payment...
        // Switch to Crypto
        processor.setPaymentStrategy(crypto);
        processor.processPayment(); // Processing crypto payment...
        // Switch to Stripe
        processor.setPaymentStrategy(stripe);
        processor.processPayment(); // Processing Stripe payment...
    }
}

/*

Output :
Processing credit card payment...
Processing PayPal payment...
Processing crypto payment...
Processing Stripe payment...

*/