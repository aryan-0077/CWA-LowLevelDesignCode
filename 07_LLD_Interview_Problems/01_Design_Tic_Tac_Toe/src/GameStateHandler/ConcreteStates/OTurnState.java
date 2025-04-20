package GameStateHandler.ConcreteStates;

import CommonEnum.Symbol;
import GameStateHandler.Context.GameContext;
import GameStateHandler.GameState;
import Utility.Player;

public class OTurnState implements GameState {
    @Override
    public void next(GameContext context, Player player , boolean hasWon) {
        if(hasWon){
            context.setState(player.getSymbol() == Symbol.X ? new XWonState() : new OWonState());
        }
        context.setState(new XTurnState());
    }

    @Override
    public boolean isGameOver() {
        return false;
    }
}
