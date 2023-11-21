package domain;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn() {
        super(PieceType.PAWN);
    }
    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color);
    }

    @Override
    public List<BoardSquare> getMoves(ChessBoard chessboard, SquarePosition position) {
        List<BoardSquare> moves = new ArrayList<>();
        int offset = this.getColor().equals(PieceColor.WHITE) ? -1 : 1;
        for (int i = 1; i <= 2; i++) {
            if (chessboard.getChessBoard()[position.getX() + (i * offset)].getRow()[position.getY()].isEmpty()) {
                moves.add(chessboard.getChessBoard()[position.getX() + (i * offset)].getRow()[position.getY()]);
            }
        }
        return moves;
    }

}
