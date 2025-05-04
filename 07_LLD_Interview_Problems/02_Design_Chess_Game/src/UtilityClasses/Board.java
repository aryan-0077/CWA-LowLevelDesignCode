package UtilityClasses;

import PieceFactoryPackage.PieceFactory;

public class Board {
    private static Board instance;
    private Cell[][] board;
    // Private constructor to prevent instantiation
    private Board(int rows) {
        initializeBoard(rows);
    }

    // Method to get the single instance of Board
    public static Board getInstance(int rows) {
        if (instance == null) {
            instance = new Board(rows);
        }
        return instance;
    }

    // Initialize the board
    private void initializeBoard(int rows) {
        board = new Cell[rows][rows];
        // Setting White Pieces using PieceFactory
        setPieceRow(0, true);
        setPawnRow(1, rows, true);
        // Setting Black Pieces using PieceFactory
        setPieceRow(rows - 1, false);
        setPawnRow(rows - 2, rows, false);
        // Defining rest of the cells having no pieces
        for (int row = 2; row < rows - 2; row++) {
            for (int col = 0; col < rows; col++) {
                board[row][col] = new Cell(row, col, null);
            }
        }
    }
    // Set the major pieces for a given row
    private void setPieceRow(int row, boolean isWhite) {
        board[row][0] = new Cell(row, 0, PieceFactory.createPiece("rook", isWhite));
        board[row][1] =
                new Cell(row, 1, PieceFactory.createPiece("knight", isWhite));
        board[row][2] =
                new Cell(row, 2, PieceFactory.createPiece("bishop", isWhite));
        board[row][3] =
                new Cell(row, 3, PieceFactory.createPiece("queen", isWhite));
        board[row][4] = new Cell(row, 4, PieceFactory.createPiece("king", isWhite));
        board[row][5] =
                new Cell(row, 5, PieceFactory.createPiece("bishop", isWhite));
        board[row][6] =
                new Cell(row, 6, PieceFactory.createPiece("knight", isWhite));
        board[row][7] = new Cell(row, 7, PieceFactory.createPiece("rook", isWhite));
    }
    // Set pawns for a given row
    private void setPawnRow(int row, int rows, boolean isWhite) {
        for (int j = 0; j < rows; j++) {
            board[row][j] =
                    new Cell(row, j, PieceFactory.createPiece("pawn", isWhite));
        }
    }

    public Cell getCell(int row, int col) {
        if (row >= 0 && row < board.length && col >= 0 && col < board.length) {
            return board[row][col];
        }
        return null;
    }
}
