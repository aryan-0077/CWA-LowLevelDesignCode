import Controller.SnakeGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            // Define game configuration
            // can be taken as user input as well
            int width = 20;
            int height = 15;
            // Define some food positions (more can be generated during gameplay)
            int[][] foodPositions = {
                    {0, 1}, // Initial food
                    {10, 8}, // Second food
                    {3, 12}, // Third food
                    {8, 17}, // Fourth food
                    {12, 3} // Fifth food
            };
            // Initialize the game
            SnakeGame game = new SnakeGame(width, height, foodPositions);
            // Display game instructions
            System.out.println("===== SNAKE GAME =====");
            System.out.println(
                    "Controls: W (Up), S (Down), A (Left), D (Right), Q (Quit)");
            System.out.println("Eat food to grow your snake and increase your score.");
            System.out.println("Don't hit the walls or bite yourself!");
            System.out.println("=======================");
            // Create scanner for user input
            Scanner scanner = new Scanner(System.in);
            boolean gameRunning = true;
            int score = 0;
            // Main game loop
            while (gameRunning) {
                // Display current game state (in a real implementation, you would
                // have a graphical representation of the board)
                displayGameState(game);
                // Get user input
                System.out.print("Enter move (W/A/S/D) or Q to quit: ");
                String input = scanner.nextLine().toUpperCase();
                // Handle quit command
                if (input.equals("Q")) {
                    System.out.println("Game ended by player. Final score: " + score);
                    gameRunning = false;
                    continue;
                }
                // Convert WASD input to UDLR for game processing
                String direction = convertInput(input);
                // Skip invalid inputs
                if (direction.isEmpty()) {
                    System.out.println("Invalid input! Use W/A/S/D to move or Q to quit.");
                    continue;
                }
                // Make the move and get the new score
                score = game.move(direction);
                // Check for game over
                if (score == -1) {
                    System.out.println("GAME OVER! You hit a wall or bit yourself.");
                    System.out.println("Final score: " + (game.snake.size() - 1));
                    gameRunning = false;
                } else {
                    System.out.println("Score: " + score);
                }
            }
            scanner.close();
            System.out.println("Thanks for playing!");
        }
        // Convert user-friendly WASD input to UDLR for the game engine
        private static String convertInput(String input) {
            switch (input) {
                case "W":
                    return "U"; // Up
                case "S":
                    return "D"; // Down
                case "A":
                    return "L"; // Left
                case "D":
                    return "R"; // Right
                default:
                    return ""; // Invalid input
            }
        }
        // A simple method to display the game state in the console
        // In a real implementation, this would be replaced with graphics
        private static void displayGameState(SnakeGame game) {
            // This is a placeholder - in a real implementation, you would
            // access the game's state and render it appropriately
            System.out.println("nCurrent snake length: " + game.getSnake().size());
            // In a complete implementation, you would render the board with the
            // snake, food, and boundaries visually
        }
}

/*

Output :

===== SNAKE GAME =====
Controls: W (Up), S (Down), A (Left), D (Right), Q (Quit)
Eat food to grow your snake and increase your score.
Don't hit the walls or bite yourself!
=======================
nCurrent snake length: 1
Enter move (W/A/S/D) or Q to quit: D
Score: 1
nCurrent snake length: 2
Enter move (W/A/S/D) or Q to quit: D
Score: 1
nCurrent snake length: 2
Enter move (W/A/S/D) or Q to quit: A
Score: 1
nCurrent snake length: 2
Enter move (W/A/S/D) or Q to quit: A
Score: 1
nCurrent snake length: 2
Enter move (W/A/S/D) or Q to quit: S
Score: 1
nCurrent snake length: 2
Enter move (W/A/S/D) or Q to quit: S
Score: 1
nCurrent snake length: 2
Enter move (W/A/S/D) or Q to quit: S
Score: 1
nCurrent snake length: 2
Enter move (W/A/S/D) or Q to quit: A
GAME OVER! You hit a wall or bit yourself.
Final score: 1
Thanks for playing!

*/