package UtilityClasses;

import PieceFactoryPackage.Piece;

public class Cell {
    private int row, col;
    private String label;
    private Piece piece;
    // constructor
    public Cell(int row, int col, Piece piece) {
        this.row = row;
        this.col = col;
        this.piece = piece;
    }
    // eturns the current piece on the cell
    public Piece getPiece() {
        return piece;
    }
    // puts a piece on the cell
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
