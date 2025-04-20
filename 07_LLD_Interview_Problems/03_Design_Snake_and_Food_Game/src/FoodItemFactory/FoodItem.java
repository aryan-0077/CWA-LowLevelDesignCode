package FoodItemFactory;

// Abstract class representing a food item in the game
public abstract class FoodItem {
    protected int row, column; // Position of the food item
    protected int points; // Points awarded when consumed
    // Constructor to initialize food item position
    public FoodItem(int row, int column) {
        this.row = row;
        this.column = column;
    }
    // Getter methods to retrieve food item properties
    public int getRow() { return row; }
    public int getColumn() { return column; }
    public int getPoints() { return points; }
}
