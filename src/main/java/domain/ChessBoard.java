package domain;

public class ChessBoard {
    private ChessBoardRow[] chessBoard;
    public ChessBoard() {
        this.chessBoard = new ChessBoardRow[8];
        for (int i = 0; i < this.chessBoard.length; i++) {
            this.chessBoard[i] = new ChessBoardRow();
        }
    }

    public ChessBoardRow[] getChessBoard() {
        return chessBoard;
    }

    public boolean isEmpty() {
        for (int i = 0; i < this.chessBoard.length; i++) {
            if (!this.chessBoard[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void initialize() {
        this.initialize(PieceColor.NULL, PieceColor.NULL);
    }

    public void initialize(PieceColor white, PieceColor black) {
        // first two rows from top down
        this.chessBoard[0] = ChessBoardRowFactory.getPieceRow(black);
        this.chessBoard[1] = ChessBoardRowFactory.getPawnRow(black);

        // middle empty rows
        for (int i = 2; i < 6; i++) {
            this.chessBoard[i] = ChessBoardRowFactory.getEmptyRow();
        }

        // bottom two rows
        this.chessBoard[6] = ChessBoardRowFactory.getPawnRow(white);
        this.chessBoard[7] = ChessBoardRowFactory.getPieceRow(white);

        // set positions of boardsquares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.getChessBoard()[i].getRow()[j].getPosition().setPosition(i,j);
            }
        }
    }

    public boolean equals(ChessBoardRow[] chessBoardRows) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!this.chessBoard[i].getRow()[j].getPiece().equals(chessBoardRows[i].getRow()[j].getPiece())) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(this.getChessBoard()[i].toString());
        }
        return sb.toString();
    }

    public void movePiece(BoardSquare source, BoardSquare destination) {
        if (source.getPiece().getMoves(this, source.getPosition()).contains(destination)) {
            Piece piece = source.removePiece();
            destination.placePiece(piece);
        }
    }

    public BoardSquare getSquare(int x, int y) {
        if ((x > 7 || x < 0) || (y > 7 || y < 0)) {
            return null;
        }
        return this.getChessBoard()[x].getRow()[y];
    }

    public Piece getPiece(int x, int y) {
        if (this.getSquare(x, y) == null) {
            return null;
        }
        return this.getSquare(x, y).getPiece();
    }

    public void placePiece(Piece piece, int x, int y) {
        this.getSquare(x, y).placePiece(piece);
    }

    public Piece removePiece(int x, int y) {
        return this.getSquare(x, y).removePiece();
    }

}
