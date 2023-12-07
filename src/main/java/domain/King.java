package domain;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    public King() {
        super(PieceType.KING);
    }

    public King(PieceColor color) {
        super(PieceType.KING, color);
    }

    @Override
    public List<BoardSquare> getMoves(ChessBoard chessboard, SquarePosition position) {
        ArrayList<BoardSquare> moves = new ArrayList<>();
        int rank = this.getOpposingColor().equals(PieceColor.BLACK) ? 7 : 0;
        // top row
        this.addMove(moves, chessboard.getSquare(position.getX() - 1, position.getY() - 1), this.getOpposingColor());
        this.addMove(moves, chessboard.getSquare(position.getX() - 1, position.getY()), this.getOpposingColor());
        this.addMove(moves, chessboard.getSquare(position.getX() - 1, position.getY() + 1), this.getOpposingColor());
        // middle row
        this.addMove(moves, chessboard.getSquare(position.getX(), position.getY() - 1), this.getOpposingColor());
        this.addMove(moves, chessboard.getSquare(position.getX(), position.getY() + 1), this.getOpposingColor());
        // bottom row
        this.addMove(moves, chessboard.getSquare(position.getX() + 1, position.getY() - 1), this.getOpposingColor());
        this.addMove(moves, chessboard.getSquare(position.getX() + 1, position.getY()), this.getOpposingColor());
        this.addMove(moves, chessboard.getSquare(position.getX() + 1, position.getY() + 1), this.getOpposingColor());
        // castle
        if (position.getX() == rank
                && this.getFirstMove()
                && chessboard.getSquare(rank, 7).getPiece().getFirstMove()) {
            if (chessboard.getSquare(rank, position.getY() + 1).isEmpty()
                    && chessboard.getSquare(rank, position.getY() + 2).isEmpty()) {
                this.addMove(moves, chessboard.getSquare(rank, position.getY() + 3));

            }
        }
        return moves;
    }


}
