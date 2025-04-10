package CoreClasses;

import CommonEnum.SeatCategory;

public class Seat {
    private final int seatId; // Unique identifier for the seat
    private final int row; // Row number where the seat is located
    private final SeatCategory seatCategory; // Category of the seat (e.g., Silver, Gold, Platinum)

    public Seat(final int seatId,final int row, final SeatCategory seatCategory) {
        this.seatId = seatId;
        this.row = row;
        this.seatCategory = seatCategory;
    }

    // Getters and Setters Section Start
    public int getSeatId() {
        return seatId;
    }
    public int getRow() {
        return row;
    }
    public SeatCategory getSeatCategory() {
        return seatCategory;
    }
    // Getters and Setters Section End
}
