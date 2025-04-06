package Services;

import CoreClasses.Booking;
import Interfaces.SeatLockProvider;
import CoreClasses.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentService {

    // Keeps track of how many times payment has failed for a particular booking.
    Map<Booking, Integer> bookingFailures;

    // Maximum number of allowed retries before seats are unlocked.
    private final Integer allowedRetries;

    // Used to unlock seats if payment fails too many times.
    private final SeatLockProvider seatLockProvider;


    public PaymentService(final Integer allowedRetries, SeatLockProvider seatLockProvider) {
        this.allowedRetries = allowedRetries;
        this.seatLockProvider = seatLockProvider;
        this.bookingFailures = new ConcurrentHashMap<>();
    }

    // Called when payment fails for a booking attempt.
    public void processPaymentFailed(final Booking booking, final User user) throws Exception{
        // Only the user who initiated the booking is allowed to report failure.
        if (!booking.getUser().equals(user)) {
            throw new Exception ();
        }

        // Initialize failure count for the booking if it's the first failure.
        if (!bookingFailures.containsKey(booking)) {
            bookingFailures.put(booking, 0);
        }

        // Increment failure count.
        final Integer currentFailuresCount = bookingFailures.get(booking);
        final Integer newFailuresCount = currentFailuresCount + 1;
        bookingFailures.put(booking, newFailuresCount);

        // If failures exceed allowed retries, unlock the seats.
        if (newFailuresCount > allowedRetries) {
            seatLockProvider.unlockSeats(booking.getShow(), booking.getSeatsBooked(), booking.getUser());
        }

    }

}


