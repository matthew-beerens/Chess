package logic;

import domain.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.BoardSquareUi;
import ui.ChessBoardUi;

import java.util.ArrayList;
import java.util.List;

public class Chess extends Application {
    private ChessBoard chessBoard;
    private ChessBoardUi ui;
    private BoardSquare selectedSquare;
    private List<BoardSquare> availableMoves;

    public void init() {
        this.ui = new ChessBoardUi();
        this.chessBoard = new ChessBoard();
        this.chessBoard.initialize(PieceColor.WHITE, PieceColor.BLACK);
        this.ui.initialize(Color.BURLYWOOD, Color.MAROON, this.chessBoard);
        this.selectedSquare = null;
        this.availableMoves = new ArrayList<>();
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chess");
        stage.setMaxWidth(720);
        stage.setMaxHeight(748);
        stage.setMinWidth(720);
        stage.setMinHeight(748);

        for (Node bsui : this.ui.getChildren()) {
            bsui.setOnMouseClicked(e -> {
                BoardSquareUi bs = (BoardSquareUi) bsui;
                this.makeMove(bs);
                this.renderPieces(this.chessBoard);
                this.ui.renderMoves(this.availableMoves);
            });
        }

        Scene scene = new Scene(this.ui);
        stage.setScene(scene);
        stage.show();

    }

    public void makeMove(BoardSquareUi bs) {
        SquarePosition position = bs.getPosition();
        if (this.selectedSquare == null && !this.chessBoard.getPiece(position.getX(), position.getY()).getType().equals(PieceType.NULL)) {
            BoardSquare selectedSquare = this.chessBoard.getSquare(position.getX(), position.getY());
            this.availableMoves = selectedSquare.getPiece().getMoves(this.chessBoard, selectedSquare.getPosition());
            this.selectedSquare = selectedSquare;
            return;
        }
        BoardSquare selectedMove = chessBoard.getSquare(bs.getPosition().getX(), bs.getPosition().getY());
        if (!this.availableMoves.contains(selectedMove)) {
            this.selectedSquare = null;
            this.availableMoves = new ArrayList<>();
            return;
        }
        this.chessBoard.movePiece(this.selectedSquare, selectedMove);
        this.selectedSquare = null;
        this.availableMoves = new ArrayList<>();
    }

    public void renderPieces(ChessBoard chessBoard) {
        this.ui.renderPieces(chessBoard);
    }
}
