import Controller.GameController.TicTacToeGame;
import PlayerStrategies.ConcreteStrategies.HumanPlayerStrategy;
import PlayerStrategies.PlayerStrategy;

// The main method serves as the entry point for the Tic-Tac-Toe game
// application. It initializes the player strategies and starts the game.
public class Main {
    public static void main(String[] args) {
        PlayerStrategy playerXStrategy = new HumanPlayerStrategy("Player X");
        PlayerStrategy playerOStrategy = new HumanPlayerStrategy("Player O");
        TicTacToeGame game = new TicTacToeGame(playerXStrategy, playerOStrategy, 3, 3);
        game.play();
    }
}

/*

Output :

 . | . | .
---+---+---
 . | . | .
---+---+---
 . | . | .

Player X, enter your move (row [0-2] and column [0-2]): 0 1
 . | X | .
---+---+---
 . | . | .
---+---+---
 . | . | .

Player O, enter your move (row [0-2] and column [0-2]): 1 2
 . | X | .
---+---+---
 . | . | O
---+---+---
 . | . | .

Player X, enter your move (row [0-2] and column [0-2]): 0 0
 X | X | .
---+---+---
 . | . | O
---+---+---
 . | . | .

Player O, enter your move (row [0-2] and column [0-2]): 1 2
Invalid move. Try again.
Player O, enter your move (row [0-2] and column [0-2]): 0 2
 X | X | O
---+---+---
 . | . | O
---+---+---
 . | . | .

Player X, enter your move (row [0-2] and column [0-2]): 1 1
 X | X | O
---+---+---
 . | X | O
---+---+---
 . | . | .

Player O, enter your move (row [0-2] and column [0-2]): 2 2
 X | X | O
---+---+---
 . | X | O
---+---+---
 . | . | O

Player O wins!

Process finished with exit code 0


*/