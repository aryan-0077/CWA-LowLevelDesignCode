package CoreClasses;

import CommonEnum.SeatCategory;

public class Seat {

    // Unique identifier for the seat
    private final int seatId;

    // Row number where the seat is located
    private final int row;

    // Category of the seat (e.g., Silver, Gold, Platinum)
    private final SeatCategory seatCategory;

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
