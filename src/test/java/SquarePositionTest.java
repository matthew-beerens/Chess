import domain.SquarePosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquarePositionTest {
    private SquarePosition position = new SquarePosition();
    @Test
    void initialPosition() {
        assertEquals(this.position.getX(), -1);
        assertEquals(this.position.getY(), -1);
    }

    @Test
    void setXY() {
        this.position.setX(0);
        this.position.setY(0);
        assertEquals(this.position.getX(), 0);
        assertEquals(this.position.getY(), 0);
    }

    @Test
    void setPosition() {
        this.position.setPosition(1, 1);
        assertEquals(this.position.getX(), 1);
        assertEquals(this.position.getY(), 1);
    }
}
