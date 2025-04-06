package Controllers;

import CoreClasses.User;
import Services.BookingService;
import Services.PaymentService;

public class PaymentController {

    // Service to handle payment-related logic
    private final PaymentService paymentService;

    // Service to handle booking-related operations
    private final BookingService bookingService;

    public PaymentController(PaymentService paymentService, BookingService bookingService) {
        this.paymentService = paymentService;
        this.bookingService = bookingService;

    }

    public void paymentFailed(final String bookingId, final User user) throws Exception {
        paymentService.processPaymentFailed(bookingService.getBooking(bookingId), user);
    }

    public void paymentSuccess(final String bookingId, final User user) throws Exception {
        bookingService.confirmBooking(bookingService.getBooking(bookingId), user);
    }
}