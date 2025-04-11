import CommonEnum.SeatCategory;
import ConcreteLockProviders.SeatLockProvider;
import ConcretePaymentStrategies.DebitCardStrategy;
import Controllers.*;
import CoreClasses.Booking;
import CoreClasses.User;
import Interfaces.PaymentStrategy;
import Interfaces.ISeatLockProvider;
import Services.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            // Initialize services
            MovieService movieService = new MovieService();
            TheatreService theatreService = new TheatreService();
            ShowService showService = new ShowService();

            // Create a seat lock provider with 10-minute timeout (600 seconds)
            ISeatLockProvider seatLockProvider = new SeatLockProvider(600);

            // Initialize booking service with the seat lock provider
            BookingService bookingService = new BookingService(seatLockProvider);

            // Initialize seat availability service
            SeatAvailabilityService seatAvailabilityService = new SeatAvailabilityService(bookingService, seatLockProvider);

            // Initialize payment service with a simple payment strategy
            PaymentService paymentService = new PaymentService(new DebitCardStrategy(), bookingService);

            // Initialize controllers
            MovieController movieController = new MovieController(movieService);
            TheatreController theatreController = new TheatreController(theatreService);
            ShowController showController = new ShowController(seatAvailabilityService, showService, theatreService, movieService);
            BookingController bookingController = new BookingController(showService, bookingService, theatreService);
            PaymentController paymentController = new PaymentController(paymentService);

            // Step 1: Create a theatre
            System.out.println("Creating a new theatre...");
            int theatreId = theatreController.createTheatre("PVR Cinemas");
            System.out.println("Theatre created with ID: " + theatreId);

            // Step 2: Create a screen in the theatre
            System.out.println("Creating a new screen...");
            int screenId = theatreController.createScreenInTheatre("Screen 1", theatreId);
            System.out.println("Screen created with ID: " + screenId);

            // Step 3: Create seats in the screen
            System.out.println("Creating seats...");
            // Create 5 rows with 10 seats each
            for (int row = 1; row <= 5; row++) {
                SeatCategory category;
                if (row == 1) {
                    category = SeatCategory.PLATINUM; // First row is premium
                } else if (row <= 3) {
                    category = SeatCategory.GOLD; // Next two rows are gold
                } else {
                    category = SeatCategory.SILVER; // Rest are silver
                }

                for (int seatNum = 1; seatNum <= 10; seatNum++) {
                    int seatId = theatreController.createSeatInScreen(row, category, screenId);
                    System.out.println("Created seat at row " + row + " with ID: " + seatId + " and category: " + category);
                }
            }

            // Step 4: Create a movie
            System.out.println("\nCreating a new movie...");
            int movieId = movieController.createMovie("Inception", 150);
            System.out.println("Movie created with ID: " + movieId);

            // Step 5: Create a show
            System.out.println("\nCreating a new show...");
            Date showTime = new Date(); // Current time
            int showId = showController.createShow(movieId, screenId, showTime, 150);
            System.out.println("Show created with ID: " + showId);

            // Step 6: Get available seats for the show
            System.out.println("\nChecking available seats...");
            List<Integer> availableSeats = showController.getAvailableSeats(showId);
            System.out.println("Available seats: " + availableSeats);

            // Step 7: Create a user
            System.out.println("\nCreating a user...");
            User user = new User("John Doe", "john.doe@example.com");
            System.out.println("User created: " + user.getUserName() + " with email: " + user.getUserEmail());

            // Step 8: Book tickets
            System.out.println("\nBooking tickets...");
            // Let's book seats with IDs 1, 2, and 3
            List<Integer> seatsToBook = Arrays.asList(1, 2, 3);
            String bookingId = bookingController.createBooking(user, showId, seatsToBook);
            System.out.println("Booking object created with ID: " + bookingId);

            // Step 9: Process payment
            System.out.println("\nProcessing payment...");
            paymentController.processPayment(bookingId, user);
            System.out.println("Payment processed successfully!");

            // Step 10: Verify booking status
            Booking booking = bookingService.getBooking(bookingId);
            System.out.println("\nBooking status: " + booking.getBookingStatus());
            System.out.println("Is booking confirmed? " + booking.isConfirmed());

            // Step 11: Check available seats again after booking
            System.out.println("\nChecking available seats after booking...");
            availableSeats = showController.getAvailableSeats(showId);
            System.out.println("Available seats: " + availableSeats);

        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


