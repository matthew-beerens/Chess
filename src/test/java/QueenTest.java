import domain.Bishop;
import domain.PieceColor;
import domain.PieceType;
import domain.Queen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
