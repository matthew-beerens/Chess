package ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainUi extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chess");
        stage.setWidth(800);
        stage.setHeight(800);
        stage.setMaxWidth(800);
        stage.setMaxHeight(800);
        stage.setMinWidth(800);
        stage.setMinHeight(800);
        stage.show();
    }
}
