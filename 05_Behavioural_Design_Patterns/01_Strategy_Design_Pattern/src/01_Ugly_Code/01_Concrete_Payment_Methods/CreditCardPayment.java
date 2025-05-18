package Ugly_Code.Concrete_Payment_Methods;

import Ugly_Code.PaymentMethod;

public class CreditCardPayment implements PaymentMethod {
    public void processPayment() {
        System.out.println("Processing credit card payment...");
    }
}