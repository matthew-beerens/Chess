package domain;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private PieceType type;
    private PieceColor color;

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
}
