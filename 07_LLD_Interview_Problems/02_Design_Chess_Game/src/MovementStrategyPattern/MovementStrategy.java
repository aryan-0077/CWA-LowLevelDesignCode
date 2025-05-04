package MovementStrategyPattern;

import UtilityClasses.Board;
import UtilityClasses.Cell;

public interface MovementStrategy {
    boolean canMove(Board board, Cell startCell, Cell endCell);
}
