package domain;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private boolean firstMove;
    public Pawn() {
        this(PieceColor.NULL);
    }
    public Pawn(PieceColor color) {
        super(PieceType.PAWN, color);
        this.firstMove = true;
    }

    public void setFirstMove(boolean notMoved) {
        this.firstMove = notMoved;
    }

    @Override
    public List<BoardSquare> getMoves(ChessBoard chessboard, SquarePosition position) {
        List<BoardSquare> moves = new ArrayList<>();
        int offset = this.getColor().equals(PieceColor.WHITE) ? -1 : 1;
        int rank = this.getColor().equals(PieceColor.WHITE) ? 6 : 1;

        int posX = position.getX() + offset;
        int posY = position.getY();

        BoardSquare leftTake = chessboard.getSquare(posX, posY -1 );
        BoardSquare moveForwardOne = chessboard.getSquare(posX, posY);
        BoardSquare takeRight = chessboard.getSquare(posX, posY + 1);
        BoardSquare moveForwardTwo = chessboard.getSquare(posX + offset, posY);

        if(leftTake != null && !leftTake.isEmpty() && leftTake.getPiece().getColor() != this.getColor()) {
            moves.add(leftTake);
        }

        if (moveForwardOne != null && moveForwardOne.isEmpty()) {
            moves.add(moveForwardOne);
        }

        if (takeRight != null && !takeRight.isEmpty() && takeRight.getPiece().getColor() != this.getColor()) {
            moves.add(takeRight);
        }

        if (moveForwardTwo != null && moveForwardOne.isEmpty() && moveForwardTwo.isEmpty() && this.firstMove && position.getX() == rank) {
            moves.add(moveForwardTwo);
        }

        return moves;


    }

}
