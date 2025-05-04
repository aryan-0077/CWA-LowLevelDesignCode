package PieceFactoryPackage.ConcretePieces;

import MovementStrategyPattern.ConcreteMovementStrategies.RookMovementStrategy;
import MovementStrategyPattern.MovementStrategy;
import PieceFactoryPackage.Piece;
import UtilityClasses.Board;
import UtilityClasses.Cell;

public class Rook extends Piece {

    public Rook(boolean isWhitePiece) {
        super(isWhitePiece, new RookMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
