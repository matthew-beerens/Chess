import domain.Bishop;
import domain.PieceColor;
import domain.PieceType;
import domain.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
