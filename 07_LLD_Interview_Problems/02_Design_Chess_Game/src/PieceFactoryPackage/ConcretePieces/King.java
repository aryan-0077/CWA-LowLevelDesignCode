package PieceFactoryPackage.ConcretePieces;

import MovementStrategyPattern.ConcreteMovementStrategies.KingMovementStrategy;
import MovementStrategyPattern.MovementStrategy;
import PieceFactoryPackage.Piece;
import UtilityClasses.Board;
import UtilityClasses.Cell;

public class King extends Piece {
    public King(boolean isWhitePiece) {
        super(isWhitePiece, new KingMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
