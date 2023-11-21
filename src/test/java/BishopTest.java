import domain.Bishop;
import domain.PieceColor;
import domain.PieceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
