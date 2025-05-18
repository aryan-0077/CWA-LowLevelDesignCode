package Ugly_Code.Concrete_Payment_Methods;

import Ugly_Code.PaymentMethod;

public class PayPalPayment implements PaymentMethod {
    public void processPayment() {
        System.out.println("Processing PayPal payment...");
    }
}