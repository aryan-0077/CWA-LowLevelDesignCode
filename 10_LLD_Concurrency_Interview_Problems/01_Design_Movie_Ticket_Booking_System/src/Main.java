import CommonEnum.SeatCategory;
import ConcreteLockProviders.InMemorySeatLockProvider;
import Controllers.*;
import CoreClasses.User;
import Interfaces.SeatLockProvider;
import Services.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialize all services
        MovieService movieService = new MovieService();
        TheatreService theatreService = new TheatreService();
        ShowService showService = new ShowService();

        // Initialize the seat lock provider with a timeout of 10 minutes (600 seconds)
        SeatLockProvider seatLockProvider = new InMemorySeatLockProvider(600);

        // Initialize booking service with lock provider
        BookingService bookingService = new BookingService(seatLockProvider);

        // Initialize seat availability service
        SeatAvailabilityService seatAvailabilityService = new SeatAvailabilityService(bookingService, seatLockProvider);

        // Initialize payments service with 3 allowed retries
        PaymentService paymentsService = new PaymentService(3, seatLockProvider);

        // Initialize controllers
        MovieController movieController = new MovieController(movieService);
        TheatreController theatreController = new TheatreController(theatreService);
        ShowController showController = new ShowController(seatAvailabilityService, showService, theatreService, movieService);
        BookingController bookingController = new BookingController(showService, bookingService, theatreService);
        PaymentController paymentsController = new PaymentController(paymentsService, bookingService);

        // Create user

        User user = new User("John Doe", "john.doe@example.com");

        String userId = user.getUserEmail();



        try {

            // 1. Create a movie
            int movieId = movieController.createMovie("Inception", 180);
            System.out.println("Created movie with ID: " + movieId);


            // 2. Create a theatre
            int theatreId = theatreController.createTheatre("PVR Cinemas");

            System.out.println("Created theatre with ID: " + theatreId);

            // 3. Create a screen in the theatre
            int screenId = theatreController.createScreenInTheatre("Screen 1", theatreId);
            System.out.println("Created screen with ID: " + screenId);

            Scanner scanner = new Scanner(System.in);

            for (int row = 1; row <= 5; row++) {
                for (int seatNo = 1; seatNo <= 10; seatNo++) {
                    System.out.println("Enter category (SILVER/GOLD/PLATINUM) for seat at row " + row + ", seat " + seatNo + ": ");
                    String input = scanner.nextLine().toUpperCase();
                    SeatCategory seatCategory = SeatCategory.valueOf(input);

                    int seatId = theatreController.createSeatInScreen(row, seatCategory, screenId);
                    System.out.println("Created seat with ID: " + seatId + " at row: " + row + ", seat: " + seatNo + ", category: " + seatCategory);
                }
            }

            // 5. Create a show for the movie in the screen
            Date showStartTime = new Date(); // Current time
            int durationInMinutes = 180; // 3 hours in minutes
            int showId = showController.createShow(movieId, screenId, showStartTime, durationInMinutes);

            System.out.println("Created show with ID: " + showId + " starting at: " + showStartTime);


            // 6. Get available seats for the show
            List<Integer> availableSeats = showController.getAvailableSeats(showId);
            System.out.println("Available seats for show: " + availableSeats);
            // 7. Book selected seats (let's book first 2 seats)
            List<Integer> seatsToBook = availableSeats.subList(0, 2);

            String bookingId = bookingController.createBooking(user, showId, seatsToBook);

            System.out.println("Created booking with ID: " + bookingId + " for seats: " + seatsToBook);

            // 8. Process payment (success)
            System.out.println("Processing payment...");
            paymentsController.paymentSuccess(bookingId, user);
            System.out.println("Payment successful! Booking confirmed.");

            // 9. Check available seats again (should be reduced by the number of booked seats)

            List<Integer> remainingSeats = showController.getAvailableSeats(showId);

            System.out.println("Remaining available seats: " + remainingSeats);
            System.out.println("Number of seats booked: " + (availableSeats.size() - remainingSeats.size()));

            // 10. Demonstrate failed payment scenario with a new booking
            List<Integer> moreSeatsToBook = remainingSeats.subList(0, 2);
            String failedBookingId = bookingController.createBooking(user, showId, moreSeatsToBook);
            System.out.println("Created another booking with ID: " + failedBookingId + " for seats: " + moreSeatsToBook);

            // Simulate payment failure
            paymentsController.paymentFailed(failedBookingId, user);
            System.out.println("Payment failed for booking ID: " + failedBookingId);

            // Wait for a while to see if seats get unlocked after multiple failures
            System.out.println("Simulating multiple payment failures...");
            for (int i = 0; i < 3; i++) {
                paymentsController.paymentFailed(failedBookingId, user);
                System.out.println("Payment attempt " + (i + 2) + " failed");
            }

            // Check if seats were unlocked after multiple failures
            List<Integer> seatsAfterFailure = showController.getAvailableSeats(showId);
            System.out.println("Available seats after payment failures: " + seatsAfterFailure);
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

