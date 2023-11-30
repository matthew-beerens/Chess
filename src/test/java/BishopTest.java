import domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopTest {
    private Bishop bishop = new Bishop();

    @Test
    void createNewBishopPiece() {
        assertEquals(this.bishop.getType(), PieceType.BISHOP);
    }

    @Test
    void bishopToString() {
        assertEquals(this.bishop.toString(), PieceType.BISHOP.toString());
    }

    @Test
    void pieceIsWhite() {
        Bishop whitePiece = new Bishop(PieceColor.WHITE);
        assertEquals(whitePiece.getColor(), PieceColor.WHITE);
    }

    @Test
    void pieceIsBlack() {
        Bishop whitePiece = new Bishop(PieceColor.BLACK);
        assertEquals(whitePiece.getColor(), PieceColor.BLACK);
    }

    @Test
    void pieceIsNotAColor() {
        Bishop nullPiece = new Bishop();
        assertEquals(nullPiece.getColor(), PieceColor.NULL);
    }

    @Test
    void getMoves() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        cb.placePiece(new Bishop(PieceColor.WHITE), 3, 5);
        Piece bishop = (Bishop) cb.getPiece(3, 5);
        BoardSquare square = cb.getSquare(3, 5);

        List<BoardSquare> moves = bishop.getMoves(cb, square.getPosition());
        ArrayList<BoardSquare> expectedMoves = new ArrayList<>();

        BoardSquare bs0 = new BoardSquare();
        bs0.getPosition().setPosition(2, 4);
        expectedMoves.add(bs0);
        BoardSquare bs1 = new BoardSquare();
        bs1.getPosition().setPosition(1, 3);
        expectedMoves.add(bs1);
        BoardSquare bs2 = new BoardSquare();
        bs2.getPosition().setPosition(2, 6);
        expectedMoves.add(bs2);
        BoardSquare bs3 = new BoardSquare();
        bs3.getPosition().setPosition(1, 7);
        expectedMoves.add(bs3);
        BoardSquare bs4 = new BoardSquare();
        bs4.getPosition().setPosition(4, 4);
        expectedMoves.add(bs4);
        BoardSquare bs5 = new BoardSquare();
        bs5.getPosition().setPosition(5, 3);
        expectedMoves.add(bs5);
        BoardSquare bs6 = new BoardSquare();
        bs6.getPosition().setPosition(4, 6);
        expectedMoves.add(bs6);
        BoardSquare bs7 = new BoardSquare();
        bs7.getPosition().setPosition(5, 7);
        expectedMoves.add(bs7);

        for (BoardSquare m :
                expectedMoves) {
            assertTrue(moves.contains(m));
        }
    }

}
