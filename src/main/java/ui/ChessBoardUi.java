package ui;

import domain.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoardUi extends GridPane {
    public ChessBoardUi() {
        super();
    }
    private String getAsset(Piece piece) {
        StringBuilder sb = new StringBuilder();
        sb.append("assets/");
        String color = piece.getColor().equals(PieceColor.BLACK) ? "black-" : "white-";
        sb.append(color);
        switch (piece.getType()) {
            case PAWN -> sb.append("pawn");
            case BISHOP -> sb.append("bishop");
            case KING -> sb.append("king");
            case KNIGHT -> sb.append("knight");
            case QUEEN -> sb.append("queen");
            case ROOK -> sb.append("rook");
            case NULL -> {return null;}
        }
        sb.append(".png");
        return sb.toString();
    }
    public void initialize(Paint color1, Paint color2, ChessBoard chessBoard) {
        Paint squareColor = color1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squareColor = squareColor.equals(color1) ? color2 : color1;
                BoardSquareUi square = new BoardSquareUi(squareColor, i, j);
                Piece piece = chessBoard.getPiece(i, j);
                String asset = this.getAsset(piece);
                if(!piece.getType().equals(PieceType.NULL)) {
                    PieceUi pieceUi = new PieceUi(asset);
                    square.getChildren().add(pieceUi);
                }
                this.add(square, j, i);
            }
            squareColor = squareColor.equals(color1) ? color2 : color1;
        }
    }

    public void renderPieces(ChessBoard chessBoard) {
        for (Node square : this.getChildren()) {
            BoardSquareUi bs = (BoardSquareUi) square;
            ObservableList<Node> children = bs.getChildren();
            Iterator<Node> iterator = children.iterator();
            while (iterator.hasNext()) {
                Node child = iterator.next();
                if (child.getClass().equals(PieceUi.class)) {
                    iterator.remove();
                }
            }
            SquarePosition sp = bs.getPosition();
            String asset = this.getAsset(chessBoard.getPiece(sp.getX(), sp.getY()));
            if (asset != null) {
                children.add(new PieceUi(asset));
            }
        }
    }

    public void renderMoves(List<BoardSquare> moves) {
        List<SquarePosition> positions = moves.stream()
                .map(square -> square.getPosition())
                .collect(Collectors.toList());

        for (Node square : this.getChildren()) {
            BoardSquareUi bs = (BoardSquareUi) square;
            ObservableList<Node> children = bs.getChildren();
            Iterator<Node> iterator = children.iterator();
            while(iterator.hasNext()) {
                Node child = iterator.next();
                if (child.getClass().equals(MoveMarkerUi.class)) {
                    iterator.remove();
                }
            }
            if (positions.contains(bs.getPosition())) {
                children.add(new MoveMarkerUi());
            }
        }

    }

}
