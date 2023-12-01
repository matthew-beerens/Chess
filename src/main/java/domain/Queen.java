package domain;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{
    public Queen() {
        super(PieceType.QUEEN);
    }

    public Queen(PieceColor color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public List<BoardSquare> getMoves(ChessBoard chessboard, SquarePosition position) {
        ArrayList<BoardSquare> moves = new ArrayList<>();
        // left top diagonal
        int y = position.getY() - 1;
        for (int x = position.getX() - 1; x >= 0; x--) {
            BoardSquare bs = chessboard.getSquare(x, y);
            if(!this.addMove(moves, bs, this.getOpposingColor())) {
                break;
            }
            y--;
        }

        // left bottom diagonal
        y = position.getY() - 1;
        for (int x = position.getX() + 1; x < 8; x++) {
            BoardSquare bs = chessboard.getSquare(x, y);
            if(!this.addMove(moves, bs, this.getOpposingColor())) {
                break;
            }
            y--;
        }

        // right top diagonal
        y = position.getY() + 1;
        for (int x = position.getX() - 1; x >= 0; x--) {
            BoardSquare bs = chessboard.getSquare(x, y);
            if(!this.addMove(moves, bs, this.getOpposingColor())) {
                break;
            }
            y++;
        }

        // right bottom diagonal
        y = position.getY() + 1;
        for (int x = position.getX() + 1; x < 8; x++) {
            BoardSquare bs = chessboard.getSquare(x, y);
            if(!this.addMove(moves, bs, this.getOpposingColor())) {
                break;
            }
            y++;
        }

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
