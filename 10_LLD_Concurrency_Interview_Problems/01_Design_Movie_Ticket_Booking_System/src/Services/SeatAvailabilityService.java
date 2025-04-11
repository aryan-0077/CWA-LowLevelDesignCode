package Services;

import CoreClasses.Seat;
import CoreClasses.Show;

import java.util.ArrayList;
import java.util.List;
import Interfaces.ISeatLockProvider;

public class SeatAvailabilityService {

    // Dependency for checking booked seats
    private final BookingService bookingService;

    // Dependency for checking currently locked (but not yet booked) seats
    private final ISeatLockProvider seatLockProvider;

    // Constructor to initialize dependencies
    public SeatAvailabilityService(final BookingService bookingService, final ISeatLockProvider seatLockProvider) {
        this.bookingService = bookingService;
        this.seatLockProvider = seatLockProvider;
    }

    public List<Seat> getAvailableSeats(final Show show) {
        // Fetch all seats for the show’s screen
        final List<Seat> allSeats = show.getScreen().getSeats();

        // Get the list of currently unavailable seats (booked or locked)
        final List<Seat> unavailableSeats = getUnavailableSeats(show);

        // Start with all seats, and remove unavailable ones
        final List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(unavailableSeats);
        return availableSeats;
    }

    private List<Seat> getUnavailableSeats(final Show show) {
        // Seats that are already booked
        final List<Seat> unavailableSeats = bookingService.getBookedSeats(show);

        // Seats that are locked (e.g., being held in another user’s session)
        unavailableSeats.addAll(seatLockProvider.getLockedSeats(show));
        return unavailableSeats;

    }
}
