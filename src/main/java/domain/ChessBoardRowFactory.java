package domain;

public class ChessBoardRowFactory {
    public static ChessBoardRow getEmptyRow() {
        return new ChessBoardRow();
    }

    public static ChessBoardRow getPawnRow() {
        return ChessBoardRowFactory.getPawnRow(PieceColor.NULL);
    }

    public static ChessBoardRow getPawnRow(PieceColor color) {
        ChessBoardRow cbr = new ChessBoardRow();
        for (int i = 0; i < 8; i++) {
            cbr.getRow()[i] = new BoardSquare();
            cbr.getRow()[i].placePiece(new Pawn(color));
        }
        return cbr;
    }

    public static ChessBoardRow getPieceRow() {
        return ChessBoardRowFactory.getPieceRow(PieceColor.NULL);
    }

    public static ChessBoardRow getPieceRow(PieceColor color) {
        ChessBoardRow cbr = new ChessBoardRow();
        cbr.getRow()[0].placePiece(new Rook(color));
        cbr.getRow()[1].placePiece(new Knight(color));
        cbr.getRow()[2].placePiece(new Bishop(color));
        cbr.getRow()[3].placePiece(new Queen(color));
        cbr.getRow()[4].placePiece(new King(color));
        cbr.getRow()[5].placePiece(new Bishop(color));
        cbr.getRow()[6].placePiece(new Knight(color));
        cbr.getRow()[7].placePiece(new Rook(color));
        return cbr;
    }
}
