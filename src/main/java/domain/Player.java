package domain;

import java.util.Objects;

public class Player {
    public PieceColor getColor() {
        return color;
    }
    public void setColor(PieceColor color) {
        this.color = color;
    }

    private PieceColor color;
    public Player(PieceColor color) {
        this.color = color;
    }
    public Player() {
        this(PieceColor.NULL);
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
