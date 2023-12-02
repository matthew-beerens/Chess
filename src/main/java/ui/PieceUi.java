package ui;

import javafx.scene.image.ImageView;

import javax.swing.text.Element;

public class PieceUi extends ImageView {
    public PieceUi(String image) {
        super(image);
        this.setFitWidth(90);
        this.setFitHeight(90);
    }
}
