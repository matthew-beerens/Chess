package domain;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{
    public Bishop() {
        super(PieceType.BISHOP);
    }
    public Bishop(PieceColor color) {
        super(PieceType.BISHOP, color);
    }

    @Override
    public List<BoardSquare> getMoves(ChessBoard chessboard, SquarePosition position) {
        ArrayList<BoardSquare> moves = new ArrayList<>();

        // left top diagonal
        int y = position.getY() - 1;
        for (int x = position.getX() - 1; x < 8; x--) {
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
        for (int x = position.getX() - 1; x < 8; x--) {
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

        return moves;
    }


}
