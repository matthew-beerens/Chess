package domain;

import java.util.Objects;

public class SquarePosition {
    private int x;
    private int y;

    public SquarePosition() {
        this(-1, -1);
    }
    public SquarePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SquarePosition)) return false;
        SquarePosition sp = (SquarePosition) o;
        return getX() == sp.getX() && getY() == sp.getY();
    }

    @Override
    public String toString(){
        return this.getX() + " " + this.getY();
    }
}

