package domain;

public class ChessBoardRow {
    private BoardSquare[] row;

    public ChessBoardRow() {
        this.row = new BoardSquare[8];
        for (int i = 0; i < 8; i++) {
            this.getRow()[i] = new BoardSquare();
        }
    }

    public BoardSquare[] getRow() {
        return this.row;
    }

    public boolean isEmpty() {
        for (int i = 0; i < this.row.length; i++) {
            if (this.row[i].getPiece().getType() != PieceType.NULL) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(this.getRow()[i].toString());
            sb.append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
