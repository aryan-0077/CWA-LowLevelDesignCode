package MovementStrategyPattern.ConcreteMovementStrategies;

import MovementStrategyPattern.MovementStrategy;
import UtilityClasses.Board;
import UtilityClasses.Cell;

public class BishopMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return true;
    }
}
