package CommonEnum;

// Enum to represent the status of a booking session

public enum BookingStatus {
    Created,// Booking has been created but not yet confirmed
    Confirmed, // Booking has been successfully confirmed
    Expired; // Booking has expired due to timeout or other factors
}