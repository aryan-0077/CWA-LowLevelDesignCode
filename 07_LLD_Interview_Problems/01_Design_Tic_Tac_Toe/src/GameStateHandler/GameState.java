package GameStateHandler;

import GameStateHandler.Context.GameContext;
import Utility.Player;

// GameState Interface
public interface GameState {
    void next(GameContext context, Player player , boolean hasWon);
    boolean isGameOver();
}