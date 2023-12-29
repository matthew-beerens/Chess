import domain.PieceColor;
import domain.PieceType;
import domain.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    public void createPlayerWhite() {
        Player white = new Player(PieceColor.WHITE);
        assertEquals(PieceColor.WHITE, white.getColor());
    }

    @Test void createPlayerBlack() {
        Player black = new Player(PieceColor.BLACK);
        assertEquals(PieceColor.BLACK, black.getColor());
    }
}
