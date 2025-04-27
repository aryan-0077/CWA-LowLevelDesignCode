package paymentStrategyPattern.ConcretePaymentStrategies;

import paymentStrategyPattern.PaymentStrategy;

public class PaypalPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        // Logic for PayPal payment processing would go here
    }
}
