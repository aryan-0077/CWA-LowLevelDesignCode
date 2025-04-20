package GameStateHandler.ConcreteStates;

import GameStateHandler.Context.GameContext;
import GameStateHandler.GameState;
import Utility.Player;

public class OWonState implements GameState {
    @Override
    public void next(GameContext context, Player player, boolean hasWon) {
        // Game over, no next state
    }

    @Override
    public boolean isGameOver() {
        return true;
    }
}
