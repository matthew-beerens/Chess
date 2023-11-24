package domain;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece{
    public Rook() {
        this(PieceColor.NULL);
    }

    public Rook(PieceColor color) {
        super(PieceType.ROOK, color);
    }

    public boolean addBoardSquare(ArrayList<BoardSquare> moves, BoardSquare bs, PieceColor color) {

        if (bs == null) {
            return false;
        }

        if (bs.getPiece().getColor().equals(this.getColor())) {
            return false;
        }

        moves.add(bs);

        if(bs.getPiece().getColor().equals(color)) {
            return false;
        }

        return true;
    }

    @Override
    public List<BoardSquare> getMoves(ChessBoard chessboard, SquarePosition position) {
        ArrayList<BoardSquare> moves = new ArrayList<>();
        PieceColor color = this.getColor().equals(PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;

        for (int i = position.getY() - 1; i >= 0; i--) { // moves to left
            BoardSquare bs = chessboard.getSquare(position.getX(), i);
            if (!this.addBoardSquare(moves, bs, color)) {
                break;
            }
        }

        for (int i = position.getY() + 1; i < 8 ; i++) { // moves to the right
            BoardSquare bs = chessboard.getSquare(position.getX(), i);
            if (!this.addBoardSquare(moves, bs, color)) {
                break;
            }
        }

        for (int i = position.getX() - 1; i >= 0; i--) { // moves above
            BoardSquare bs = chessboard.getSquare(i, position.getY());
            if (!this.addBoardSquare(moves, bs, color)) {
                break;
            }
        }

        for (int i = position.getX() + 1; i < 8; i++) { // moves below
            BoardSquare bs = chessboard.getSquare(i, position.getY());
            if (!this.addBoardSquare(moves, bs, color)) {
                break;
            }
        }
        return moves;
    }

}
