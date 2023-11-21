package domain;

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
        return null;
    }


}
