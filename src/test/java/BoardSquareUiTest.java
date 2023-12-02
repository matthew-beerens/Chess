import domain.SquarePosition;
import javafx.scene.paint.Color;

import org.junit.jupiter.api.Test;
import ui.BoardSquareUi;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardSquareUiTest {
    @Test
    void initializeCorrect() {
        BoardSquareUi bsui = new BoardSquareUi(Color.RED, 0, 0);
        SquarePosition sp = new SquarePosition(0,0);
        assertEquals(bsui.getPosition(), sp);
        sp.setPosition(1, 1);
        bsui.setPosition(1, 1);
        assertEquals(bsui.getPosition(), sp);
    }

}
