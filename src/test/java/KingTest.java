import domain.Bishop;
import domain.King;
import domain.PieceColor;
import domain.PieceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
