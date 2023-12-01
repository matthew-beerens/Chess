import domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenTest {
    private Queen queen = new Queen();

    @Test
    void createNewQueenPiece() {
        assertEquals(this.queen.getType(), PieceType.QUEEN);
    }

    @Test
    void queenToString() {
        assertEquals(this.queen.toString(), PieceType.QUEEN.toString());
    }

    @Test
    void pieceIsWhite() {
        Queen whitePiece = new Queen(PieceColor.WHITE);
        assertEquals(whitePiece.getColor(), PieceColor.WHITE);
    }

    @Test
    void pieceIsBlack() {
        Queen whitePiece = new Queen(PieceColor.BLACK);
        assertEquals(whitePiece.getColor(), PieceColor.BLACK);
    }

    @Test
    void pieceIsNotAColor() {
        Queen nullPiece = new Queen();
        assertEquals(nullPiece.getColor(), PieceColor.NULL);
    }

    @Test
    void queenGetMoves() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        cb.placePiece(new Queen(PieceColor.WHITE), 3, 5);
        SquarePosition sp = cb.getSquare(3, 5).getPosition();
        List<BoardSquare> moves = cb.getPiece(3, 5).getMoves(cb, sp);
        List<BoardSquare> expectedAvailableMoves = new ArrayList<>();

        for (int i = sp.getY() - 1; i >= 0; i--) { // moves to left
            if (cb.getSquare(sp.getX(), i).getPiece().getColor().equals(PieceColor.WHITE)) {
                break;
            }

            BoardSquare bs = new BoardSquare();
            bs.getPosition().setPosition(sp.getX(), i);
            expectedAvailableMoves.add(bs);

            if(cb.getSquare(sp.getX(), i).getPiece().getColor().equals(PieceColor.BLACK)) {
                break;
            }
        }

        for (int i = sp.getY() + 1; i < 8 ; i++) { // moves to the right
            if (cb.getSquare(sp.getX(), i).getPiece().getColor().equals(PieceColor.WHITE)) {
                break;
            }

            BoardSquare bs = new BoardSquare();
            bs.getPosition().setPosition(sp.getX(), i);
            expectedAvailableMoves.add(bs);

            if(cb.getSquare(sp.getX(), i).getPiece().getColor().equals(PieceColor.BLACK)) {
                break;
            }
        }

        for (int i = sp.getX() - 1; i >= 0; i--) { // moves above
            if (cb.getSquare(i, sp.getY()).getPiece().getColor().equals(PieceColor.WHITE)) {
                break;
            }

            BoardSquare bs = new BoardSquare();
            bs.getPosition().setPosition(i, sp.getY());
            expectedAvailableMoves.add(bs);

            if(cb.getSquare(i, sp.getY()).getPiece().getColor().equals(PieceColor.BLACK)) {
                break;
            }
        }

        for (int i = sp.getX() + 1; i < 8; i++) { // moves below
            if (cb.getSquare(i, sp.getY()).getPiece().getColor().equals(PieceColor.WHITE)) {
                break;
            }

            BoardSquare bs = new BoardSquare();
            bs.getPosition().setPosition(i, sp.getY());
            expectedAvailableMoves.add(bs);

            if(cb.getSquare(i, sp.getY()).getPiece().getColor().equals(PieceColor.BLACK)) {
                break;
            }
        }

        BoardSquare bs0 = new BoardSquare();
        bs0.getPosition().setPosition(2, 4);
        expectedAvailableMoves.add(bs0);
        BoardSquare bs1 = new BoardSquare();
        bs1.getPosition().setPosition(1, 3);
        expectedAvailableMoves.add(bs1);
        BoardSquare bs2 = new BoardSquare();
        bs2.getPosition().setPosition(2, 6);
        expectedAvailableMoves.add(bs2);
        BoardSquare bs3 = new BoardSquare();
        bs3.getPosition().setPosition(1, 7);
        expectedAvailableMoves.add(bs3);
        BoardSquare bs4 = new BoardSquare();
        bs4.getPosition().setPosition(4, 4);
        expectedAvailableMoves.add(bs4);
        BoardSquare bs5 = new BoardSquare();
        bs5.getPosition().setPosition(5, 3);
        expectedAvailableMoves.add(bs5);
        BoardSquare bs6 = new BoardSquare();
        bs6.getPosition().setPosition(4, 6);
        expectedAvailableMoves.add(bs6);
        BoardSquare bs7 = new BoardSquare();
        bs7.getPosition().setPosition(5, 7);
        expectedAvailableMoves.add(bs7);

        for (BoardSquare bs :
                expectedAvailableMoves) {
            assertTrue(moves.contains(bs));
        }

    }

}
