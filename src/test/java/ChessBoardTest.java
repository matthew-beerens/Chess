import domain.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChessBoardTest {
    private ChessBoard chessBoard = new ChessBoard();

    @Test
    void chessBoardCorrectSize() {
        assertEquals(this.chessBoard.getChessBoard().length, 8);
    }

    @Test
    void chessBoardEmpty() {
        assertTrue(this.chessBoard.isEmpty());
    }

    @Test
    void chessBoardInitializedCorrectly() {

        ChessBoardRow[] chessBoardRows = new ChessBoardRow[8];
        chessBoardRows[0] = ChessBoardRowFactory.getPieceRow();
        chessBoardRows[1] = ChessBoardRowFactory.getPawnRow();
        for (int i = 2; i < 6; i++) {
            chessBoardRows[i] = ChessBoardRowFactory.getEmptyRow();
        }
        chessBoardRows[6] = ChessBoardRowFactory.getPawnRow();
        chessBoardRows[7] = ChessBoardRowFactory.getPieceRow();

        this.chessBoard.initialize();

        assertTrue(this.chessBoard.equals(chessBoardRows));
    }

    @Test
    void realChessBoardDefaultSetting() {

        ChessBoard chessBoardCompared = new ChessBoard();
        chessBoardCompared.initialize(PieceColor.WHITE, PieceColor.BLACK);

        ChessBoardRow[] chessBoardRows = new ChessBoardRow[8];
        chessBoardRows[0] = ChessBoardRowFactory.getPieceRow(PieceColor.BLACK);
        chessBoardRows[1] = ChessBoardRowFactory.getPawnRow(PieceColor.BLACK);
        for (int i = 2; i < 6; i++) {
            chessBoardRows[i] = ChessBoardRowFactory.getEmptyRow();
        }
        chessBoardRows[6] = ChessBoardRowFactory.getPawnRow(PieceColor.WHITE);
        chessBoardRows[7] = ChessBoardRowFactory.getPieceRow(PieceColor.WHITE);

        assertTrue(chessBoardCompared.equals(chessBoardRows));

    }

    @Test
    void chessBoardToString() {
        this.chessBoard.initialize();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(this.chessBoard.getChessBoard()[i].toString());
        }
        assertEquals(this.chessBoard.toString(), sb.toString());
    }

    @Test
    void chessBoardMovePawnPiece() {
        this.chessBoard.initialize(PieceColor.WHITE, PieceColor.BLACK);
        ChessBoard chessBoardMoved = new ChessBoard();
        chessBoardMoved.initialize(PieceColor.WHITE, PieceColor.BLACK);
        chessBoardMoved.getChessBoard()[6].getRow()[0] = new BoardSquare();
        chessBoardMoved.getChessBoard()[5].getRow()[0] = new BoardSquare();
        chessBoardMoved.getChessBoard()[5].getRow()[0].placePiece(new Pawn(PieceColor.WHITE));
        this.chessBoard.movePiece(this.chessBoard.getChessBoard()[6].getRow()[0], this.chessBoard.getChessBoard()[5].getRow()[0]);
        assertTrue(this.chessBoard.equals(chessBoardMoved.getChessBoard()));
    }

    @Test
    void getSquareAtIndex() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        chessBoard.initialize(PieceColor.WHITE, PieceColor.BLACK);
        BoardSquare bs = cb.getSquare(1, 0);
        BoardSquare cs = new BoardSquare();
        cs.getPosition().setPosition(1, 0);
        assertTrue(bs.equals(cs));
        assertEquals(cb.getSquare(-1, -1), null);
        assertEquals(cb.getSquare(8, 8), null);
        assertEquals(cb.getSquare(7, -1), null);
        assertEquals(cb.getSquare(-1, 7), null);
    }

    @Test
    void getPieceAtIndex() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        Piece blackPawn = cb.getPiece(1, 0);
        assertEquals(blackPawn, new Pawn(PieceColor.BLACK));
        assertEquals(cb.getPiece(8, -1), null);
    }

    @Test
    void placePieceAtIndex() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        cb.placePiece(new Pawn(PieceColor.WHITE), 1, 0);
        assertEquals(cb.getPiece(1, 0), new Pawn(PieceColor.WHITE));
    }

    @Test
    void removePieceAtIndex() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        Piece piece = cb.removePiece(1, 0);
        assertEquals(chessBoard.getPiece(1, 0), new NullPiece());
        assertEquals(piece, new Pawn(PieceColor.BLACK));
    }
}
