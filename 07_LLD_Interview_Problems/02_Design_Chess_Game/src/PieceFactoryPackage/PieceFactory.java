package PieceFactoryPackage;

import PieceFactoryPackage.ConcretePieces.*;

public class PieceFactory {
    public static Piece createPiece(String pieceType, boolean isWhitePiece) {
        switch (pieceType.toLowerCase()) {
            case "king":
                return new King(isWhitePiece);
            case "queen":
                return new Queen(isWhitePiece);
            case "bishop":
                return new Bishop(isWhitePiece);
            case "knight":
                return new Knight(isWhitePiece);
            case "rook":
                return new Rook(isWhitePiece);
            case "pawn":
                return new Pawn(isWhitePiece);
            default:
                throw new IllegalArgumentException("Unknown piece type: " + pieceType);
        }
    }
}
