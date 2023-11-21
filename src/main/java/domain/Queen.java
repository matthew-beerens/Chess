package domain;

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
        return null;
    }


}
