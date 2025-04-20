package GameStateHandler.Context;

import GameStateHandler.ConcreteStates.XTurnState;
import GameStateHandler.GameState;
import Utility.Player;

public class GameContext {
    private GameState currentState;
    public GameContext() {
        currentState = new XTurnState(); // Start with X's turn
    }
    public void setState(GameState state) {
        this.currentState = state;
    }
    public void next(Player player, boolean hasWon) {
        currentState.next(this, player , hasWon);
    }
    public boolean isGameOver() {
        return currentState.isGameOver();
    }
    public GameState getCurrentState() {
        return currentState;
    }
}