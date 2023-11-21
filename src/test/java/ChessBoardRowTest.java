import domain.ChessBoardRow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChessBoardRowTest {
    @Test
    void chessBoardRowCorrectLength() {
        ChessBoardRow chessBoardRow = new ChessBoardRow();
        assertEquals(chessBoardRow.getRow().length, 8);
    }

    @Test
    void chessBoardRowEmpty() {
        ChessBoardRow chessBoardRow = new ChessBoardRow();
        assertTrue(chessBoardRow.isEmpty());
    }

    @Test
    void chessBoardRowToString() {
        ChessBoardRow chessBoardRow = new ChessBoardRow();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(chessBoardRow.getRow()[i].toString());
            sb.append(" ");
        }
        sb.append("\n");
        assertEquals(chessBoardRow.toString(), sb.toString());
    }
}
