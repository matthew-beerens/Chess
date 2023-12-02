package ui;

import domain.SquarePosition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class BoardSquareUi extends StackPane {
    SquarePosition position;

    public BoardSquareUi(Paint color) {
        super();
        this.position = new SquarePosition();
        this.getChildren().add(new Rectangle(90, 90, color));
    }

    public SquarePosition getPosition() {
        return this.position;
    }
}
