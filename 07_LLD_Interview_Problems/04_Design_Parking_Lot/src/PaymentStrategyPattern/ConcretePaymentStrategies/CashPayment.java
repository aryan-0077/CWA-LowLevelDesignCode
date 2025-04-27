package PaymentStrategyPattern.ConcretePaymentStrategies;

import PaymentStrategyPattern.PaymentStrategy;

public class CashPayment implements PaymentStrategy {
    public CashPayment(double fee) {
    }

    @Override
    public void processPayment(double amount) {
            System.out.println("Processing cash payment of $" + amount);
            // Logic for cash payment processing
    }
}
