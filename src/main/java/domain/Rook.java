package domain;

import java.util.List;

public class Rook extends Piece{
    public Rook() {
        super(PieceType.ROOK);
    }

    public Rook(PieceColor color) {
        super(PieceType.ROOK, color);
    }

    @Override
    public List<BoardSquare> getMoves(ChessBoard chessboard, SquarePosition position) {
        return null;
    }

}
