import domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightTest {
    private Knight knight = new Knight();

    @Test
    void createNewKnightPiece() {
        assertEquals(knight.getType(), PieceType.KNIGHT);
    }

    @Test
    void knightToString() {
        assertEquals(this.knight.toString(), PieceType.KNIGHT.toString());
    }

    @Test
    void pieceIsWhite() {
        Knight whitePiece = new Knight(PieceColor.WHITE);
        assertEquals(whitePiece.getColor(), PieceColor.WHITE);
    }

    @Test
    void pieceIsBlack() {
        Knight whitePiece = new Knight(PieceColor.BLACK);
        assertEquals(whitePiece.getColor(), PieceColor.BLACK);
    }

    @Test
    void pieceIsNotAColor() {
        Knight nullPiece = new Knight();
        assertEquals(nullPiece.getColor(), PieceColor.NULL);
    }
    
    @Test
    void knightGetMoves() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        Piece knight = cb.getPiece(7, 1);
        List<BoardSquare> moves = knight.getMoves(cb, cb.getSquare(7, 1).getPosition());
        ArrayList<BoardSquare> expectedMoves = new ArrayList<>();
        
        BoardSquare bs0 = new BoardSquare();
        bs0.getPosition().setPosition(5, 0);
        BoardSquare bs1 = new BoardSquare();
        bs1.getPosition().setPosition(5, 2);
        
        expectedMoves.add(bs0);
        expectedMoves.add(bs1);

        // initial move
        for (BoardSquare m :
                expectedMoves) {
            moves.contains(m);
        }

        cb.placePiece(new Knight(PieceColor.WHITE), 3, 3);
        List<BoardSquare> allMoves = cb.getPiece(3,3).getMoves(cb, cb.getSquare(3,3).getPosition());
        assertEquals(allMoves.size(), 8);

    }
}
