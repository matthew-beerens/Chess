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

            int posX = position.getX() + (i * offset);
            int posY = position.getY();

            if ((posX > 7 || posX < 0) || (posY > 7 || posY < 0)) {
                break;
            }

            if (chessboard.getSquare(posX, posY).isEmpty()) {
                moves.add(chessboard.getSquare(posX, posY));
            }
        }

        return moves;
    }

}
