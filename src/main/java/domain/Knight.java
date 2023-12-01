package domain;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight() {
        super(PieceType.KNIGHT);
    }

    public Knight(PieceColor color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    public List<BoardSquare> getMoves(ChessBoard chessboard, SquarePosition position) {
        ArrayList<BoardSquare> moves = new ArrayList<>();
        // top  left
        this.addMove(moves, chessboard.getSquare(position.getX() - 2, position.getY() - 1), this.getOpposingColor());
        // top right
        this.addMove(moves, chessboard.getSquare(position.getX() - 2, position.getY() + 1), this.getOpposingColor());
        // left left
        this.addMove(moves, chessboard.getSquare(position.getX() - 1, position.getY() - 2), this.getOpposingColor());
        // left right
        this.addMove(moves, chessboard.getSquare(position.getX() + 1, position.getY() - 2), this.getOpposingColor());
        // right left
        this.addMove(moves, chessboard.getSquare(position.getX() - 1, position.getY() + 2), this.getOpposingColor());
        // right right
        this.addMove(moves, chessboard.getSquare(position.getX() + 1, position.getY() + 2), this.getOpposingColor());
        // bottom left
        this.addMove(moves, chessboard.getSquare(position.getX() + 2, position.getY() - 1), this.getOpposingColor());
        // bottom right
        this.addMove(moves, chessboard.getSquare(position.getX() + 2, position.getY() + 1), this.getOpposingColor());

        return moves;
    }


}
