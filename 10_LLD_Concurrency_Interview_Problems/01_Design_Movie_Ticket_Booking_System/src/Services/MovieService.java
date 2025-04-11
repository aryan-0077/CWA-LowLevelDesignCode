package Services;

import CoreClasses.Movie;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MovieService {

    private final Map<Integer, Movie> movies;
    private final AtomicInteger movieCounter; // Private counter for generating movie IDs

    public MovieService() {
        this.movies = new HashMap<>();
        this.movieCounter = new AtomicInteger(0); // Initialize the counter to 0
    }

    public Movie getMovie(final int movieId) throws Exception {
        if (!movies.containsKey(movieId)) {
            throw new Exception("Movie with ID " + movieId + " not found.");
        }
        return movies.get(movieId);
    }

    public Movie createMovie(final String movieName, final int durationInMinutes) {
        int movieId = movieCounter.incrementAndGet(); // Increment the counter and get the new value.
        Movie movie = new Movie(movieId, movieName, durationInMinutes);
        movies.put(movieId, movie);
        return movie;
    }
}