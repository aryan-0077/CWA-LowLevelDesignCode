package PieceFactoryPackage.ConcretePieces;

import MovementStrategyPattern.ConcreteMovementStrategies.BishopMovementStrategy;
import MovementStrategyPattern.MovementStrategy;
import PieceFactoryPackage.Piece;
import UtilityClasses.Board;
import UtilityClasses.Cell;

public class Bishop extends Piece {
    public Bishop(boolean isWhitePiece) {
        super(isWhitePiece, new BishopMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
