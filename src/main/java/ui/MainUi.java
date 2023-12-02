package ui;

import domain.ChessBoard;
import domain.PieceColor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainUi extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chess");
        stage.setMaxWidth(720);
        stage.setMaxHeight(750);
        stage.setMinWidth(720);
        stage.setMinHeight(750);

        ChessBoardUi board = new ChessBoardUi();
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        board.initialize(Color.BURLYWOOD, Color.MAROON, cb);

        Scene scene = new Scene(board);
        stage.setScene(scene);
        stage.show();

    }
}
