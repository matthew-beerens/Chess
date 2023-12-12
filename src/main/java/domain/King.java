package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class King extends Piece{
    public King() {
        super(PieceType.KING);
    }

    public King(PieceColor color) {
        super(PieceType.KING, color);
    }

    public void removeChecked(ChessBoard chessBoard, ArrayList<BoardSquare> moves, SquarePosition king) {
        List<BoardSquare> coveredMoves = new ArrayList<>();

        for (ChessBoardRow bs : chessBoard.getChessBoard()) {
            for (BoardSquare square: bs.getRow()) {
                Piece piece = square.getPiece();
                if (piece.getType().equals(PieceType.KING)
                        || piece.getType().equals(PieceType.NULL)
                        || !piece.getColor().equals(this.getOpposingColor())) {
                    continue;
                }
                coveredMoves.addAll(square.getPiece().getMoves(chessBoard, square.getPosition()));
            }
        }

        Iterator iterator = moves.iterator();
        while(iterator.hasNext()) {
            BoardSquare move = (BoardSquare) iterator.next();
            if (coveredMoves.contains(move)) {
                iterator.remove();
            }
        }
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
                && chessboard.getSquare(rank, 7).getPiece().getFirstMove()) {
            if (chessboard.getSquare(rank, position.getY() + 1).isEmpty()
                    && chessboard.getSquare(rank, position.getY() + 2).isEmpty()) {
                this.addMove(moves, chessboard.getSquare(rank, position.getY() + 3));

            }
        }
        removeChecked(chessboard, moves, position);
        return moves;
    }


}
