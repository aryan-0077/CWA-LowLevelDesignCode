package CoreClasses;

import java.util.ArrayList;
import java.util.List;

public class Screen {


    // Unique identifier for the screen

    private final int id;

    // Name of the screen
    private final String name;


    // The theater to which this screen belongs
    private final Theatre theatre;

    // List of seats available in this screen
    private final List<Seat> seats;

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
