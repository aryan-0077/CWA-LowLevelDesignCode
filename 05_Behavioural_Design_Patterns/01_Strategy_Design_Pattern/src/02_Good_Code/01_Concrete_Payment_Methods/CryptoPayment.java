package Good_Code.Concrete_Payment_Methods;


import Good_Code.PaymentStrategy;

public class CryptoPayment implements PaymentStrategy {
    public void processPayment() {
        System.out.println("Processing crypto payment...");
    }
}