package Services;

import CoreClasses.Booking;
import CoreClasses.Show;
import CoreClasses.Seat;
import CoreClasses.User;
import Interfaces.ISeatLockProvider;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BookingService {

    // Stores all bookings made across shows (key = booking ID)
    // Changed to a thread-safe concurrent map.
    private final Map<String, Booking> showBookings;

    // Provider responsible for handling temporary seat locks
    private final ISeatLockProvider seatLockProvider;

    // Atomic integer to generate unique booking IDs
    private final AtomicInteger bookingIdCounter = new AtomicInteger(1);

    // Constructor to initialize dependencies
    public BookingService(ISeatLockProvider seatLockProvider) {
        this.seatLockProvider = seatLockProvider;
        this.showBookings = new ConcurrentHashMap<>();
    }

    public Booking getBooking(final String bookingId) throws Exception  {
        if (!showBookings.containsKey(bookingId)) {
            throw new Exception("No Booking exists for the ID : " + bookingId);
        }
        return showBookings.get(bookingId);
    }

    public List<Booking> getAllBookings(final Show show) {
        List<Booking> response = new ArrayList<>();
        for (Booking booking : showBookings.values()) {
            if (booking.getShow().equals(show)) response.add(booking);
        }
        return response;
    }

    public Booking createBooking(final User user, final Show show, final List<Seat> seats) throws Exception {
        // Check if any requested seat is already booked
        if (isAnySeatAlreadyBooked(show, seats)) throw new Exception("Seat Already Booked");

        // Lock the seats temporarily for the user (this will throw an exception if any seat is already locked)
        seatLockProvider.lockSeats(show, seats, user);

        // Create a new booking with a unique booking ID using AtomicInteger
        final String bookingId = String.valueOf(bookingIdCounter.getAndIncrement());

        final Booking newBooking = new Booking(bookingId, show, user, seats);

        // Save the booking
        showBookings.put(bookingId, newBooking);
        return newBooking;
    }

    public List<Seat> getBookedSeats(final Show show) {
        return getAllBookings(show).stream()
                .filter(Booking::isConfirmed)      // Only confirmed bookings
                .map(Booking::getSeatsBooked)      // Extract booked seats
                .flatMap(Collection::stream)       // Flatten seat lists
                .collect(Collectors.toList());
    }

    public void confirmBooking(final Booking booking, final User user) throws Exception {
        if (!booking.getUser().equals(user)) {
            throw new Exception("Cannot confirm a booking made by another user"); // User mismatch
        }

        // Validate locks for each seat
        for (Seat seat : booking.getSeatsBooked()) {
            if (!seatLockProvider.validateLock(booking.getShow(), seat, user)) {
                throw new Exception("Acquired Lock is either invalid or has Expired");
            }
        }
        // Mark booking as confirmed
        booking.confirmBooking();
    }

    private boolean isAnySeatAlreadyBooked(final Show show, final List<Seat> seats) {
        final List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat : seats) {
            if (bookedSeats.contains(seat)) return true;
        }
        return false;
    }
}
