package CoreClasses;

public class Movie {

    private final int movieId;
    private final String movieName;
    private final int movieDurationInMinutes;

    public Movie(int i, String movieName, int movieDurationInMinutes) {
        this.movieId = i;
        this.movieName = movieName;
        this.movieDurationInMinutes = movieDurationInMinutes;
    }

    // Getters and Setters Section Start
    public int getMovieId() {return movieId;}
    public String getMovieName() {return movieName;}
    public int getMovieDuration() { return movieDurationInMinutes;}
    // Getters and Setters Section End
}