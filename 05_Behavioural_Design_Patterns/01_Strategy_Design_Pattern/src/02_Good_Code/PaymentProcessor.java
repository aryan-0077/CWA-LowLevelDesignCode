package Good_Code;

public class PaymentProcessor {
    private PaymentStrategy paymentStrategy; // Reference to a payment strategy
    // Constructor to set the payment strategy
    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Process payment using the current strategy
    public void processPayment() {
        paymentStrategy
                .processPayment(); // Delegate the payment processing to the strategy
    }

    // Dynamically change payment strategy at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}