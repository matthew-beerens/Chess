import domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTest {
    private Rook rook = new Rook();

    @Test
    void createNewRookPiece() {
        assertEquals(this.rook.getType(), PieceType.ROOK);
    }

    @Test
    void rookToString() {
        assertEquals(this.rook.toString(), PieceType.ROOK.toString());
    }

    @Test
    void pieceIsWhite() {
        Rook whitePiece = new Rook(PieceColor.WHITE);
        assertEquals(whitePiece.getColor(), PieceColor.WHITE);
    }

    @Test
    void pieceIsBlack() {
        Rook whitePiece = new Rook(PieceColor.BLACK);
        assertEquals(whitePiece.getColor(), PieceColor.BLACK);
    }

    @Test
    void pieceIsNotAColor() {
        Rook nullPiece = new Rook();
        assertEquals(nullPiece.getColor(), PieceColor.NULL);
    }

    @Test
    void getMoves() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        cb.placePiece(new Rook(PieceColor.WHITE), 3, 3);
        SquarePosition sp = cb.getSquare(3, 3).getPosition();
        List<BoardSquare> moves = cb.getPiece(3, 3).getMoves(cb, sp);
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

        for (BoardSquare bs :
                expectedAvailableMoves) {
            assertTrue(moves.contains(bs));
        }

    }
}
