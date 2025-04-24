package Controller;

import MovementStrategyPackage.ConcreteMovementStrategies.HumanMovementStrategy;
import MovementStrategyPackage.MovementStrategy;
import UtilityClasses.GameBoard;
import UtilityClasses.Pair;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SnakeGame {
    private GameBoard board;
    public Deque<Pair> snake;
    private Map<Pair, Boolean> snakeMap;
    private int[][] food;
    private int foodIndex;
    private MovementStrategy movementStrategy;

    // Initialize the game with specified dimensions and food positions.
    public SnakeGame(int width, int height, int[][] food) {
        this.board = GameBoard.getInstance(width, height);
        this.food = food;
        this.foodIndex = 0;

        // Initialize snake
        this.snake = new LinkedList<>();
        this.snakeMap = new HashMap<>();
        Pair initialPos = new Pair(0, 0);
        this.snake.offerFirst(initialPos);
        this.snakeMap.put(initialPos, true);

        // Set default movement strategy
        this.movementStrategy = new HumanMovementStrategy();
    }

    // Set the movement strategy (Human or AI)
    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    // Returns the new score or -1 if game over.
    public int move(String direction) {
        // Get current head
        Pair currentHead = this.snake.peekFirst();

        // Get next position using strategy pattern
        Pair newHead = this.movementStrategy.getNextPosition(currentHead, direction);
        int newHeadRow = newHead.getRow();
        int newHeadColumn = newHead.getCol();
        // Check boundary conditions
        boolean crossesBoundary = newHeadRow < 0 || newHeadRow >= this.board.getHeight() ||
                newHeadColumn < 0 || newHeadColumn >= this.board.getWidth();

        // Get current tail for collision check
        Pair currentTail = this.snake.peekLast();

        // Check if snake bites itself (excluding tail which will move away)
        boolean bitesItself = this.snakeMap.containsKey(newHead) &&
                !(newHead.getRow() == currentTail.getRow() &&
                        newHead.getCol() == currentTail.getCol());

        // Game over conditions
        if (crossesBoundary || bitesItself) {
            return -1;
        }
        // Check if snake eats food
        boolean ateFood = (this.foodIndex < this.food.length) &&
                (this.food[this.foodIndex][0] == newHeadRow) &&
                (this.food[this.foodIndex][1] == newHeadColumn);
        if (ateFood) {
            // Increment food index to move to next food
            this.foodIndex++;
        } else {
            // If no food eaten, remove tail
            this.snake.pollLast();
            this.snakeMap.remove(currentTail);
        }
        // Add new head
        this.snake.addFirst(newHead);
        this.snakeMap.put(newHead, true);
        // Calculate ans return score
        int score = this.snake.size() - 1;
        return score;
    }

    public Deque<Pair> getSnake() {
        return snake;
    }
}
