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
    private Player white;
    private Player black;
    private Player currentTurn;

    public void init() {
        this.ui = new ChessBoardUi();
        this.chessBoard = new ChessBoard();
        this.chessBoard.initialize(PieceColor.WHITE, PieceColor.BLACK);
        this.ui.initialize(Color.rgb(222,184,135), Color.rgb(139,69,19), this.chessBoard);
        this.selectedSquare = null;
        this.availableMoves = new ArrayList<>();
        this.white = new Player(PieceColor.WHITE);
        this.black = new Player(PieceColor.BLACK);
        this.currentTurn = this.white;
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chess");
        stage.setMaxWidth(736);
        stage.setMaxHeight(758);
        stage.setMinWidth(736);
        stage.setMinHeight(758);

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

        if(this.setSourceSquare(position)) {
            return;
        }

        BoardSquare selectedMove = this.selectedMove(position);

        if(!this.validateMove(selectedMove)) {
            return;
        }

        this.move(selectedMove);
        this.resetState();
        this.switchPlayer();
    }

    public void renderPieces(ChessBoard chessBoard) {
        this.ui.renderPieces(chessBoard);
    }

    public boolean setSourceSquare(SquarePosition position) {
        if (this.selectedSquare == null && !this.chessBoard.getPiece(position.getX(), position.getY())
                .getType()
                .equals(PieceType.NULL))
        {
            BoardSquare selectedSquare = this.chessBoard.getSquare(position.getX(), position.getY());
            this.availableMoves = selectedSquare.getPiece()
                    .getSafeMoves(this.chessBoard, selectedSquare);
            this.selectedSquare = selectedSquare;
            return true;
        }
        return false;
    }

    public BoardSquare selectedMove(SquarePosition position) {
        return chessBoard.getSquare(position.getX(), position.getY());
    }

    public boolean validateMove(BoardSquare selectedMove) {
        if (!this.availableMoves.contains(selectedMove)) {
            this.selectedSquare = null;
            this.availableMoves = new ArrayList<>();
            return false;
        }
        if (!this.selectedSquare.getPiece()
                .getColor()
                .equals(this.currentTurn.getColor())) {
            return false;
        }
        return true;
    }

    public void resetState() {
        this.selectedSquare = null;
        this.availableMoves.clear();
    }

    public void switchPlayer() {
        this.currentTurn = this.currentTurn.equals(white) ? this.black : this.white;
    }
    public void move(BoardSquare selectedMove) {
        this.chessBoard.movePiece(this.selectedSquare, selectedMove);
    }
}
