package CoreClasses;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final int id; // Unique identifier for the screen
    private final String name;  // Name of the screen
    private final Theatre theatre;  // The theater to which this screen belongs
    private final List<Seat> seats;   // List of seats available in this screen

    public Screen(final int id, final String name, final Theatre theatre) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
        this.seats = new ArrayList<>();
    }
    public void addSeat(final Seat seat) {
        this.seats.add(seat);
    }

    // Getters and Setters Section Start
    public int getScreenId() {
        return id;
    }
    public List<Seat> getSeats() {
        return seats;
    }
    public Theatre getTheatre() {
        return theatre;
    }
    // Getters and Setters Section End
}
