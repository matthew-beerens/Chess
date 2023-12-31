package domain;

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private PieceType type;
    private PieceColor color;

    private boolean firstMove;

    public Piece() {
        this(PieceType.NULL, PieceColor.NULL);
    }
    public Piece(PieceType type) {
        this(type, PieceColor.NULL);
    }
    public Piece(PieceColor pieceColor) {
        this(PieceType.NULL, pieceColor);
    }
    public Piece(PieceType pieceType, PieceColor pieceColor) {
        this.type = pieceType;
        this.color = pieceColor;
        this.firstMove = true;
    }

    public boolean getFirstMove() {
        return this.firstMove;
    }

    public void setFirstMove(boolean val) {
        this.firstMove = val;
    }
    public PieceType getType() {
        return type;
    }
    public void setType(PieceType type) {
        this.type = type;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public String toString() {
        return this.type.toString();
    }

    public PieceColor getOpposingColor() {
        return this.getColor().equals(PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece piece)) return false;
        return getType() == piece.getType() && getColor() == piece.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType());
    }

    public abstract List<BoardSquare> getMoves(ChessBoard chessboard, SquarePosition position);

    public List<BoardSquare> getSafeMoves(ChessBoard chessboard, BoardSquare source) {
        List<BoardSquare> destinations = this.getMoves(chessboard, source.getPosition());
        this.filterDangerousMoves(chessboard, source, destinations);
        return destinations;
    }

    public boolean addMove(ArrayList<BoardSquare> moves, BoardSquare bs, PieceColor color) {

        if (bs == null) {
            return false;
        }

        if (bs.getPiece().getColor().equals(this.getColor())) {
            return false;
        }

        moves.add(bs);

        return !(bs.getPiece().getColor().equals(color));
    }

    public boolean addMove(ArrayList<BoardSquare> moves, BoardSquare bs) {

        if (bs == null) {
            return false;
        }

        moves.add(bs);

        return !(bs.getPiece().getColor().equals(color));
    }

    public void filterDangerousMoves(ChessBoard chessBoard, BoardSquare source, List<BoardSquare> destinations) {
        Iterator it = destinations.iterator();
        while (it.hasNext()) {
            BoardSquare destination = (BoardSquare) it.next();
            if (chessBoard.isDangerousMove(source, destination)) {
                it.remove();
            }
        }
    }

}
