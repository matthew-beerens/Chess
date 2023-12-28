import domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingTest {
    private King king = new King();
    @Test
    void createNewKingPiece() {
        assertEquals(king.getType(), PieceType.KING);
    }

    @Test
    void kingToString() {
        assertEquals(this.king.toString(), PieceType.KING.toString());
    }

    @Test
    void pieceIsWhite() {
        King whitePiece = new King(PieceColor.WHITE);
        assertEquals(whitePiece.getColor(), PieceColor.WHITE);
    }

    @Test
    void pieceIsBlack() {
        King whitePiece = new King(PieceColor.BLACK);
        assertEquals(whitePiece.getColor(), PieceColor.BLACK);
    }

    @Test
    void pieceIsNotAColor() {
        King nullPiece = new King();
        assertEquals(nullPiece.getColor(), PieceColor.NULL);
    }

    @Test
    void kingGetMoves() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        List<BoardSquare> moves = cb.getPiece(7,4).getMoves(cb, cb.getSquare(7, 4).getPosition());
        ArrayList<BoardSquare> expectedMoves = new ArrayList<>();
        assertTrue(moves.isEmpty());
        cb.placePiece(new King(PieceColor.WHITE), 2, 4);
        
        BoardSquare bs0 = new BoardSquare();
        bs0.getPosition().setPosition(1, 3);
        expectedMoves.add(bs0);

        BoardSquare bs1 = new BoardSquare();
        bs1.getPosition().setPosition(1, 4);
        expectedMoves.add(bs1);

        BoardSquare bs2 = new BoardSquare();
        bs2.getPosition().setPosition(1, 5);
        expectedMoves.add(bs2);

        BoardSquare bs3 = new BoardSquare();
        bs3.getPosition().setPosition(2, 3);
        expectedMoves.add(bs3);
        
        BoardSquare bs4 = new BoardSquare();
        bs4.getPosition().setPosition(2, 5);
        expectedMoves.add(bs4);

        BoardSquare bs5 = new BoardSquare();
        bs5.getPosition().setPosition(3, 3);
        expectedMoves.add(bs5);

        BoardSquare bs6 = new BoardSquare();
        bs6.getPosition().setPosition(3, 4);
        expectedMoves.add(bs6);

        BoardSquare bs7 = new BoardSquare();
        bs7.getPosition().setPosition(3, 5);
        expectedMoves.add(bs7);

        for (BoardSquare m :
                expectedMoves) {
            moves.contains(m);
        }
    }

    @Test
    void castle() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        cb.removePiece(7, 5);
        cb.removePiece(7, 6);
        List<BoardSquare> moves = cb.getPiece(7, 4).getMoves(cb, cb.getSquare(7, 4).getPosition());
        assertEquals(2, moves.size());
    }

    @Test
    void checkedMoveZero() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        cb.removePiece(6, 4);
        cb.placePiece(new Bishop(PieceColor.BLACK), 5, 5);
        cb.setDangerousMoves();
        List<BoardSquare> moves = cb.getPiece(7, 4).getSafeMoves(cb, cb.getSquare(7, 4));
        System.out.println(cb);
        assertEquals(0, moves.size());
    }

    @Test
    void checkedMoveOne() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        cb.removePiece(6, 4);
        cb.placePiece(new Bishop(PieceColor.WHITE), 5, 5);
        List<BoardSquare> moves1 = cb.getPiece(7, 4).getSafeMoves(cb, cb.getSquare(7, 4));
        cb.setDangerousMoves();
        assertEquals(1, moves1.size());
    }
}
