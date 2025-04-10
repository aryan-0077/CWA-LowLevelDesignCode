package ConcretePaymentStrategies;

import Interfaces.PaymentStrategy;

public class DebitCardStrategy implements PaymentStrategy {
    @Override
    public boolean processPayment() {
        // In a real-world scenario, this would include logic to integrate with a payment gateway,
        // validate card details, handle 3D secure authentication, check balance, and process the transaction.
        return true;
    }
}
