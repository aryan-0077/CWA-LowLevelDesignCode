package CommonEnum;

// Enum to represent the status of a booking session

public enum BookingStatus {
    CREATED,// Booking has been created but not yet confirmed
    CONFIRMED, // Booking has been successfully confirmed
    EXPIRED; // Booking has expired due to timeout or other factors
}