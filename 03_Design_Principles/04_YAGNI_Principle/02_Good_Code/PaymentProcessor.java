package Good_Code;

// Good Code: Meets the interviewer's requirements without adding unnecessary complexity

class PaymentProcessor {

    private String paymentMethod;

    public PaymentProcessor(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Processes payment for supported methods only
    public void processPayment(double amount) {

        if (paymentMethod.equalsIgnoreCase("CreditCard")) {
            System.out.println("Processing payment of $" + amount + " via Credit Card.");
        } else if (paymentMethod.equalsIgnoreCase("DebitCard")) {
            System.out.println("Processing payment of $" + amount + " via Debit Card.");
        } else {
            System.out.println("Payment method not supported.");
        }
    }
}
