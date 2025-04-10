package Controllers;

import CoreClasses.User;
import Services.BookingService;
import Services.PaymentService;

public class PaymentController {

    // Service to handle payment-related logic
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processPayment(final String bookingId, final User user) throws Exception {
        paymentService.processPayment(bookingId, user);
    }
}