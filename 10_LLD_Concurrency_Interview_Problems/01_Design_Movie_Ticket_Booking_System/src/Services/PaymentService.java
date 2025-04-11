package Services;

import CoreClasses.Booking;
import Interfaces.PaymentStrategy;
import Interfaces.ISeatLockProvider;
import CoreClasses.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentService {

    // Keeps track of how many times payment has failed for a particular booking.
    Map<Booking, Integer> bookingFailures;

    // The strategy which the user will decide to do the payment
    private final PaymentStrategy paymentStrategy;
    private BookingService bookingService;

    public PaymentService(PaymentStrategy paymentStrategy, BookingService bookingService) {
        this.bookingFailures = new ConcurrentHashMap<>();
        this.paymentStrategy = paymentStrategy;
        this.bookingService = bookingService;
    }

    // Called when payment fails for a booking attempt.
    public void processPaymentFailed(final String bookingId, final User user) throws Exception{
        // Only the user who initiated the booking is allowed to report failure.
        Booking booking = bookingService.getBooking(bookingId);

        if (!booking.getUser().equals(user)) {
            throw new Exception("Only the booking owner can report payment failure.");
        }

        // Initialize failure count for the booking if it's the first failure.
        if (!bookingFailures.containsKey(booking)) bookingFailures.put(booking, 0);

        // Increment failure count.
        final Integer currentFailuresCount = bookingFailures.get(booking);
        final Integer newFailuresCount = currentFailuresCount + 1;
        bookingFailures.put(booking, newFailuresCount);
        System.out.println("Could not process the payment for Booking with ID : " + bookingId);
    }

    public void processPayment(final String bookingId, final User user) throws Exception {
        if(paymentStrategy.processPayment()){
            bookingService.confirmBooking(bookingService.getBooking(bookingId), user);
        }else {
            processPaymentFailed(bookingId, user);
        }
    }
}


