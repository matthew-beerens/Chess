import domain.Bishop;
import domain.Knight;
import domain.PieceColor;
import domain.PieceType;
import org.junit.jupiter.api.Test;

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
}
