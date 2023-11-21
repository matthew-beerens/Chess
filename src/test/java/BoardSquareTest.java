import domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardSquareTest {
    @Test
    void emptySquare() {
        BoardSquare square = new BoardSquare();
        assertTrue(square.isEmpty());
    }

    @Test
    void placePiece() {
        BoardSquare square = new BoardSquare();
        Pawn pawn = new Pawn();
        square.placePiece(pawn);
        assertEquals(square.getPiece(), pawn);
    }

    @Test
    void removePiece() {
        BoardSquare square = new BoardSquare();
        square.placePiece(new Pawn());
        Piece piece = square.removePiece();
        assertEquals(piece.getType(), PieceType.PAWN);
        assertEquals(square.getPiece().getType(), PieceType.NULL);
    }

    @Test
    void boardSquareToString() {
        BoardSquare square = new BoardSquare();
        assertEquals(square.toString(), square.getPiece().toString());
    }

    @Test
    void getBoardSquarePosition() {
        BoardSquare square = new BoardSquare();
        assertEquals(square.getPosition().getX(), -1);
        assertEquals(square.getPosition().getY(), -1);
    }

    @Test
    void initializedBoardSquarePosition() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize();
        BoardSquare bs = chessBoard.getChessBoard()[0].getRow()[0];
        assertEquals(bs.getPosition().getX(), 0);
        assertEquals(bs.getPosition().getY(), 0);
    }

}
