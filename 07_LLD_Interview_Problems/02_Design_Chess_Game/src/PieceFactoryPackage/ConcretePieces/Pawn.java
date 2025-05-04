package PieceFactoryPackage.ConcretePieces;

import MovementStrategyPattern.ConcreteMovementStrategies.PawnMovementStrategy;
import MovementStrategyPattern.MovementStrategy;
import PieceFactoryPackage.Piece;
import UtilityClasses.Board;
import UtilityClasses.Cell;

public class Pawn extends Piece {
    public Pawn(boolean isWhitePiece) {
        super(isWhitePiece, new PawnMovementStrategy());
    }

    @Override
    public boolean canMove(Board board, Cell startCell, Cell endCell) {
        return super.canMove(board, startCell, endCell);
    }
}
