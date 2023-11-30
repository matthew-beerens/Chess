package domain;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
    public Rook() {
        this(PieceColor.NULL);
    }

    public Rook(PieceColor color) {
        super(PieceType.ROOK, color);
    }

    @Override
    public List<BoardSquare> getMoves(ChessBoard chessboard, SquarePosition position) {
        ArrayList<BoardSquare> moves = new ArrayList<>();

        for (int i = position.getY() - 1; i >= 0; i--) { // moves to left
            BoardSquare bs = chessboard.getSquare(position.getX(), i);
            if (!this.addMove(moves, bs, this.getOpposingColor())) {
                break;
            }
        }

        for (int i = position.getY() + 1; i < 8 ; i++) { // moves to the right
            BoardSquare bs = chessboard.getSquare(position.getX(), i);
            if (!this.addMove(moves, bs, this.getOpposingColor())) {
                break;
            }
        }

        for (int i = position.getX() - 1; i >= 0; i--) { // moves above
            BoardSquare bs = chessboard.getSquare(i, position.getY());
            if (!this.addMove(moves, bs, this.getOpposingColor())) {
                break;
            }
        }

        for (int i = position.getX() + 1; i < 8; i++) { // moves below
            BoardSquare bs = chessboard.getSquare(i, position.getY());
            if (!this.addMove(moves, bs, this.getOpposingColor())) {
                break;
            }
        }
        return moves;
    }

}
