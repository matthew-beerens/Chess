package domain;

import java.util.Objects;

public class BoardSquare {
    private Piece piece;
    private SquarePosition position;

    public BoardSquare() {
        this.piece = new NullPiece();
        this.position = new SquarePosition();
    }

    public boolean isEmpty() {
        if (this.piece.getType() == PieceType.NULL) {
            return true;
        }
        return false;
    }

    public void placePiece(Piece piece) {
        this.piece = piece;
    }
    public Piece removePiece() {
        Piece piece = this.piece;
        this.piece = new NullPiece();
        return piece;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public SquarePosition getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof BoardSquare)) {
            return false;
        }

        BoardSquare bs = (BoardSquare) o;
        if (this.getPosition().getX() == bs.getPosition().getX() && this.getPosition().getY() == bs.getPosition().getY()) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return this.getPiece().toString();
    }
}
