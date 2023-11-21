package domain;

import java.util.ArrayList;
import java.util.List;

public class NullPiece extends Piece {
    @Override
    public List<BoardSquare> getMoves(ChessBoard chessboard, SquarePosition position) {
        return new ArrayList<>();
    }
}
