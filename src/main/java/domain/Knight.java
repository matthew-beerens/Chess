package domain;

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
        return null;
    }


}
