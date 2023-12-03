package logic;

import domain.BoardSquare;
import domain.ChessBoard;
import domain.PieceColor;
import domain.SquarePosition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.BoardSquareUi;
import ui.ChessBoardUi;
import java.util.List;

public class Chess extends Application {
    private ChessBoard chessBoard;
    private ChessBoardUi ui;
    public void init() {
        this.ui = new ChessBoardUi();
        this.chessBoard = new ChessBoard();
        this.chessBoard.initialize(PieceColor.WHITE, PieceColor.BLACK);
        this.ui.initialize(Color.BURLYWOOD, Color.MAROON, this.chessBoard);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chess");
        stage.setMaxWidth(720);
        stage.setMaxHeight(750);
        stage.setMinWidth(720);
        stage.setMinHeight(750);

        for (Node bsui : this.ui.getChildren()) {
            bsui.setOnMouseClicked(e -> {
                BoardSquareUi bs = (BoardSquareUi) bsui;
                this.makeMove(bs.getPosition());
                this.renderPieces(this.chessBoard);
            });
        }

        Scene scene = new Scene(this.ui);
        stage.setScene(scene);
        stage.show();

    }

    public void makeMove(SquarePosition position) {
        System.out.println(position);
        System.out.println(this.chessBoard);
        BoardSquare selectedSquare = this.chessBoard.getSquare(position.getX(), position.getY());
        List<BoardSquare> moves = selectedSquare.getPiece().getMoves(this.chessBoard, selectedSquare.getPosition());
        if (moves.size() > 0) {
            chessBoard.movePiece(selectedSquare, moves.get(0));
        }
        System.out.println(this.chessBoard);
    }

    public void renderPieces(ChessBoard chessBoard) {
        this.ui.renderPieces(chessBoard);
    }
}
