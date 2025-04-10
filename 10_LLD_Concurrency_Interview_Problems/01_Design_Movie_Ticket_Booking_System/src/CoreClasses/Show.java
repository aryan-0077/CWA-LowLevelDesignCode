package CoreClasses;

import java.util.Date;

public class Show {

    // Unique identifier for the show
    private final int id;
    // The movie being shown
    private final Movie movie;

    // The screen where the show is played
    private final Screen screen;

    // Start time of the show
    private final Date startTime;

    // Duration of the show in seconds
    private final Integer durationInMinutes;

    public Show(final int id, final Movie movie, final Screen screen, final Date startTime, final Integer durationInMinutes) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
    }
    // Getters Section Start
    public int getId() {
        return id;
    }
    public Movie getMovie() { return movie;}
    public Screen getScreen() {
        return screen;
    }
    public Date getStartTime() {
        return startTime;
    }
    public Integer getdurationInMinutes() {
        return durationInMinutes;
    }
    // Getters Section End
}