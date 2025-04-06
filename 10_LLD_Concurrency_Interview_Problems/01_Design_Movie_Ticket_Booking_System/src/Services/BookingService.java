package Services;

import CoreClasses.Booking;
import CoreClasses.Show;
import CoreClasses.Seat;
import CoreClasses.User;
import Interfaces.SeatLockProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BookingService {

    // Stores all bookings made across shows (key = booking ID)
    private final Map<String, Booking> showBookings;

    // Provider responsible for handling temporary seat locks
    private final SeatLockProvider seatLockProvider;

    // Atomic integer to generate unique booking IDs
    private final AtomicInteger bookingIdCounter = new AtomicInteger(1);



    // Constructor to initialize dependencies
    public BookingService(SeatLockProvider seatLockProvider) {
        this.seatLockProvider = seatLockProvider;
        this.showBookings = new HashMap<>();

    }

    public Booking getBooking(final String bookingId) throws Exception  {
        if (!showBookings.containsKey(bookingId)) {
            throw new Exception ();
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

    public Booking createBooking(final User user, final Show show, final List<Seat> seats) throws Exception{
        // Check if any requested seat is already booked
        if (isAnySeatAlreadyBooked(show, seats)) throw new Exception ();

        // Lock the seats temporarily for the user
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
                .filter(Booking::isConfirmed)                 // Only confirmed bookings
                .map(Booking::getSeatsBooked)                 // Extract booked seats
                .flatMap(Collection::stream)                 // Flatten seat lists
                .collect(Collectors.toList());
    }

    public void confirmBooking(final Booking booking, final User user) throws Exception {
        if (!booking.getUser().equals(user)) {
            throw new Exception (); // User mismatch
        }

        // Validate locks for each seat
        for (Seat seat : booking.getSeatsBooked()) {
            if (!seatLockProvider.validateLock(booking.getShow(), seat, user)) {
                throw new Exception(); // Lock invalid or expired
            }
        }
        // Mark booking as confirmed
        booking.confirmBooking();
    }


    private boolean isAnySeatAlreadyBooked(final Show show, final List<Seat> seats) {
        final List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat : seats) {
            if (bookedSeats.contains(seat))  return true;
        }
        return false;
    }
}