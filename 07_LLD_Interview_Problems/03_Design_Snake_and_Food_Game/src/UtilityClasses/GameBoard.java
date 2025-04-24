package UtilityClasses;

// Singleton class representing the game board
public class GameBoard {
    private static GameBoard instance; // Single instance of the game board
    private int width;  // Width of the game board
    private int height; // Height of the game board
    // Private constructor to prevent direct instantiation
    private GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }
    // Method to get the singleton instance of the game board
    public static GameBoard getInstance(int width, int height) {
        if (instance == null) {
            instance = new GameBoard(width, height); // Create instance if not already created
        }
        return instance; // Return the existing instance
    }
    // Getter methods to retrieve board dimensions
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
