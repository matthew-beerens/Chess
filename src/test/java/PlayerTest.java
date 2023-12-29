import domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    private Player player = new Player();
    @Test
    public void createPlayerWhite() {
        Player white = new Player(PieceColor.WHITE);
        assertEquals(PieceColor.WHITE, white.getColor());
    }

    @Test
    public void createPlayerBlack() {
        Player black = new Player(PieceColor.BLACK);
        assertEquals(PieceColor.BLACK, black.getColor());
    }

    @Test
    public void capturePiece() {
        this.player.setColor(PieceColor.WHITE);
        this.player.capturePiece(new Pawn(PieceColor.BLACK));
        assertEquals(1, this.player.getCaptured().size());
    }
}
