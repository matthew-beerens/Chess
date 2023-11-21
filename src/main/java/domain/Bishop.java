package domain;

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
        return null;
    }


}
