package MovementStrategyPattern.ConcreteMovementStrategies;

import MovementStrategyPattern.MovementStrategy;
import UtilityClasses.Board;
import UtilityClasses.Cell;

public class PawnMovementStrategy implements MovementStrategy {
    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return false;
    }
}
