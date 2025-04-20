package Utility;

// Position.java
public class Position {
    public int row;
    public int col;

    // Constructor to initialize the position
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Optional: Override toString for better debugging or printing
    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    // Optional: Equals and hashCode if you ever want to compare positions or use in collections
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;
        Position other = (Position) obj;
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {
        return 31 * row + col;
    }
}

