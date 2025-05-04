package PieceFactoryPackage;

import MovementStrategyPattern.MovementStrategy;
import UtilityClasses.Board;
import UtilityClasses.Cell;

public abstract class Piece {
    private boolean isWhitePiece; // is the piece white piece or black piece
    private boolean killed = false;
    private MovementStrategy movementStrategy;
    public Piece(boolean isWhitePiece, MovementStrategy movementStrategy) {
        this.isWhitePiece = isWhitePiece;
        this.movementStrategy = movementStrategy;
    }
    public boolean isWhite() {
        return isWhitePiece;
    }
    public boolean isKilled() {
        return killed;
    }
    public void setKilled(boolean killed) {
        this.killed = killed;
    }
    public boolean canMove(Board board, Cell startBlock, Cell endBlock) {
        return movementStrategy.canMove(board, startBlock, endBlock);
    }
}
