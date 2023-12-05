package ui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MoveMarkerTakeUi extends Circle {
    public MoveMarkerTakeUi() {
        super(39);
        this.setFill(Color.rgb(0,0,0,0));
        this.setStroke(Color.rgb(50,50,50,0.5));
        this.setStrokeWidth(10);
    }
}
