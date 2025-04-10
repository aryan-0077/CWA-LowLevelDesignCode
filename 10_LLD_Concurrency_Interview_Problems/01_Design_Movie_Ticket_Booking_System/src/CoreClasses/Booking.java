package CoreClasses;

import CommonEnum.BookingStatus;

import java.util.List;

public class Booking {

    private final String id; // Unique identifier for this booking.
    private final Show show; // The specific show for which the booking is made.
    private final List<Seat> seatsBooked; // The list of seats that are part of this booking.
    private final User user; // The user who made this booking.
    private BookingStatus bookingStatus; // The current status of the booking (e.g., Created, Confirmed, Expired).

    public Booking(final String id, final Show show, final User user, final List<Seat> seatsBooked) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = BookingStatus.CREATED; // Initial booking status is set to Created.
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.CONFIRMED;
    }

    public void confirmBooking() throws Exception {
        if (this.bookingStatus != BookingStatus.CREATED) {
            throw new Exception("Cannot confirm a booking that is not in the Created state.");
        }
        this.bookingStatus = BookingStatus.CONFIRMED; // Update the booking status to Confirmed.
    }

    public void expireBooking() throws Exception {
        if (this.bookingStatus != BookingStatus.CREATED) {
            throw new Exception("Cannot expire a booking that is not in the Created state.");
        }
        this.bookingStatus = BookingStatus.EXPIRED; // Update the booking status to expire.
    }

    // Getters Section Start
    public String getId() {
        return id;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeatsBooked() {
        return seatsBooked;
    }

    public User getUser() {
        return user;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }
    // Getters Section End
}
