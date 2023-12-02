package ui;

import domain.ChessBoard;
import domain.Piece;
import domain.PieceColor;
import domain.PieceType;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

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

}
