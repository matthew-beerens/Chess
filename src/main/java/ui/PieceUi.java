package ui;

import javafx.scene.image.ImageView;

public class PieceUi extends ImageView {
    public PieceUi(String image) {
        super(image);
        this.setFitWidth(90);
        this.setFitHeight(90);
    }
}
