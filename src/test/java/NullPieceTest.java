import domain.NullPiece;
import domain.PieceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NullPieceTest {
    private NullPiece nullPiece = new NullPiece();

    @Test
    void createNewNullPiece() {
        assertEquals(this.nullPiece.getType(), PieceType.NULL);
    }

    @Test
    void nullPieceToString() {
        assertEquals(this.nullPiece.toString(), PieceType.NULL.toString());
    }

}
