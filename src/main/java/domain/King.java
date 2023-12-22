package domain;

import java.util.*;

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
                && chessboard.getSquare(rank, 7).getPiece().getFirstMove()
                && chessboard.getSquare(rank, 7).getPiece().getType().equals(PieceType.ROOK)) {
            if (chessboard.getSquare(rank, position.getY() + 1).isEmpty()
                    && chessboard.getSquare(rank, position.getY() + 2).isEmpty()) {
                this.addMove(moves, chessboard.getSquare(rank, position.getY() + 3));

            }
        }

        ArrayList<BoardSquare> dangerousMoves = this.getColor().equals(PieceColor.WHITE) ? chessboard.getBlackMoves() : chessboard.getWhiteMoves();

        Iterator iterator = moves.iterator();
        while(iterator.hasNext()) {
            BoardSquare move = (BoardSquare) iterator.next();
            if (dangerousMoves.contains(move)) {
                iterator.remove();
            }
        }

        return moves;
    }


}
