package PieceFactoryPackage.ConcretePieces;

import MovementStrategyPattern.ConcreteMovementStrategies.QueenMovementStrategy;
import MovementStrategyPattern.MovementStrategy;
import PieceFactoryPackage.Piece;
import UtilityClasses.Board;
import UtilityClasses.Cell;

public class Queen extends Piece {
    public Queen(boolean isWhitePiece) {
        super(isWhitePiece, new QueenMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
