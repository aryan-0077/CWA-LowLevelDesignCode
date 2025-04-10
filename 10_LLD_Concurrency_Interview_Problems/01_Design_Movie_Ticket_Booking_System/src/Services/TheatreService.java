package Services;


import CommonEnum.SeatCategory;
import CoreClasses.Screen;
import CoreClasses.Seat;
import CoreClasses.Theatre;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TheatreService {

    // Maps to hold all created theatres, screens, and seats
    private final Map<Integer, Theatre> theatres;
    private final Map<Integer, Screen> screens;
    private final Map<Integer, Seat> seats;

    // Atomic counters for generating unique IDs
    private final AtomicInteger theatreCounter;
    private final AtomicInteger screenCounter;
    private final AtomicInteger seatCounter;

    // Constructor initializing all maps and counters
    public TheatreService() {
        this.theatres = new HashMap<>();
        this.screens = new HashMap<>();
        this.seats = new HashMap<>();
        this.theatreCounter = new AtomicInteger(0);
        this.screenCounter = new AtomicInteger(0);
        this.seatCounter = new AtomicInteger(0);
    }

    // Retrieves a seat by ID, throws exception if not found
    public Seat getSeat(final int seatId) throws Exception {
        if (!seats.containsKey(seatId)) {
            throw new Exception("Seat with ID " + seatId + " not found.");
        }
        return seats.get(seatId);
    }

    // Retrieves a theatre by ID, throws exception if not found

    public Theatre getTheatre(final int theatreId) throws Exception{
        if (!theatres.containsKey(theatreId)) {
            throw new Exception("Theatre with ID " + theatreId + " not found.");
        }
        return theatres.get(theatreId);
    }

    // Retrieves a screen by ID, throws exception if not found
    public Screen getScreen(final int screenId) throws Exception  {
        if (!screens.containsKey(screenId)) {
            throw new Exception("Screen with ID " + screenId + " not found.");
        }
        return screens.get(screenId);
    }


    // Creates a new theatre with a unique ID and stores it
    public Theatre createTheatre(final String theatreName) {
        int theatreId = theatreCounter.incrementAndGet(); // Generate unique ID
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatres.put(theatreId, theatre); // Store theatre in map
        return theatre;
    }

    // Creates a new screen in the given theatre and links it
    public Screen createScreenInTheatre(final String screenName, final Theatre theatre) {
        Screen screen = createScreen(screenName, theatre); // Create screen
        theatre.addScreen(screen); // Add to theatre
        return screen;
    }

    // Creates a new seat in the given screen and stores it
    public Seat createSeatInScreen(final Integer rowNo, SeatCategory seatCategory,  final Screen screen) {
        int seatId = seatCounter.incrementAndGet(); // Generate unique seat ID
        Seat seat = new Seat(seatId, rowNo, seatCategory);
        seats.put(seatId, seat); // Store seat in map
        screen.addSeat(seat); // Link seat to screen
        return seat;
    }

    // Private helper to create a screen with unique ID and store it
    private Screen createScreen(final String screenName, final Theatre theatre) {
        int screenId = screenCounter.incrementAndGet(); // Generate unique ID
        Screen screen = new Screen(screenId, screenName, theatre);
        screens.put(screenId, screen); // Store screen in map
        return screen;
    }
}