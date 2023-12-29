package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
    private final List<Piece> captured;
    private PieceColor color;
    public PieceColor getColor() {
        return color;
    }
    public void setColor(PieceColor color) {
        this.color = color;
    }

    public Player(PieceColor color) {
        this.color = color;
        this.captured = new ArrayList<>();
    }

    public Player() {
        this(PieceColor.NULL);
    }

    public List<Piece> getCaptured() {
        return captured;
    }

    public void capturePiece(Piece piece) {
        this.captured.add(piece);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return color == player.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
