package domain;

public class ChessBoard {
    private ChessBoardRow[] chessBoard;
    public ChessBoard() {
        this.chessBoard = new ChessBoardRow[8];
        for (int i = 0; i < this.chessBoard.length; i++) {
            this.chessBoard[i] = new ChessBoardRow();
        }
    }

    public ChessBoardRow[] getChessBoard() {
        return chessBoard;
    }

    public boolean isEmpty() {
        for (int i = 0; i < this.chessBoard.length; i++) {
            if (!this.chessBoard[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void initialize() {
        this.initialize(PieceColor.NULL, PieceColor.NULL);
    }

    public void initialize(PieceColor white, PieceColor black) {
        // first two rows from top down
        this.chessBoard[0] = ChessBoardRowFactory.getPieceRow(black);
        this.chessBoard[1] = ChessBoardRowFactory.getPawnRow(black);

        // middle empty rows
        for (int i = 2; i < 6; i++) {
            this.chessBoard[i] = ChessBoardRowFactory.getEmptyRow();
        }

        // bottom two rows
        this.chessBoard[6] = ChessBoardRowFactory.getPawnRow(white);
        this.chessBoard[7] = ChessBoardRowFactory.getPieceRow(white);

        // set positions of boardsquares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.getChessBoard()[i].getRow()[j].getPosition().setPosition(i,j);
            }
        }
    }

    public boolean equals(ChessBoardRow[] chessBoardRows) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!this.chessBoard[i].getRow()[j].getPiece().equals(chessBoardRows[i].getRow()[j].getPiece())) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(this.getChessBoard()[i].toString());
        }
        return sb.toString();
    }

    public void movePiece(BoardSquare source, BoardSquare destination) {
        // valid move check
        if (!source.getPiece().getMoves(this, source.getPosition()).contains(destination)) {
            return;
        }
        // castle logic
        if (checkCastle(source, destination)) {
            this.castle(source, destination);
            return;
        }
        // enpassant logic
        if (checkEnpassant(source, destination)) {
            this.enpassant(source, destination);
            return;
        }
        this.clearEnpassantables();
        if (checkEnpassantable(source, destination)) {
            this.setEnpassantable((Pawn) source.getPiece());
        }
        // normal move
        Piece piece = source.removePiece();
        piece.setFirstMove(false);
        destination.placePiece(piece);
    }

    public boolean checkCastle(BoardSquare source, BoardSquare destination) {
        if (source.getPiece().getType().equals(PieceType.KING) && destination.getPiece().getType().equals(PieceType.ROOK)) {
            return true;
        }
        return false;
    }

    public void castle(BoardSquare source, BoardSquare destination) {
        Piece king = source.removePiece();
        Piece rook = destination.removePiece();
        king.setFirstMove(false);
        rook.setFirstMove(false);
        this.placePiece(king, source.getPosition().getX(), destination.getPosition().getY() - 1);
        this.placePiece(rook, destination.getPosition().getX(), source.getPosition().getY() + 1);
    }

    public boolean checkEnpassantable(BoardSquare source, BoardSquare destination) {
        if (source.getPiece().getType().equals(PieceType.PAWN)
                && ((source.getPosition().getX() - destination.getPosition().getX() == 2)
                || (source.getPosition().getX() - destination.getPosition().getX() == -2))) {
            return true;
        }
        return false;
    }

    public void setEnpassantable(Pawn pawn) {
        pawn.setEnpassantable(true);
    }

    public boolean checkEnpassant(BoardSquare source, BoardSquare destination) {
        if (!source.getPiece().getType().equals(PieceType.PAWN)) {
            return false;
        }
        if (!destination.getPiece().getType().equals(PieceType.PAWN)) {
            return false;
        }
        int difference = source.getPosition().getY() - destination.getPosition().getY();
        if (!(difference == 1 || difference == -1)) {
            return false;
        }
        Pawn pawn = (Pawn) destination.getPiece();
        return pawn.isEnpassantable();
    }

    public void enpassant(BoardSquare source, BoardSquare destination) {
        PieceColor direction = source.getPiece().getOpposingColor();
        int x = direction.equals(PieceColor.BLACK) ? -1 : 1;
        Piece piece = this.removePiece(source.getPosition().getX(), source.getPosition().getY());
        this.removePiece(destination.getPosition().getX(), destination.getPosition().getY());
        this.placePiece(piece, destination.getPosition().getX() + x, destination.getPosition().getY());
    }

    public void clearEnpassantables() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = this.getPiece(i,j);
                if (piece.getType().equals(PieceType.PAWN)) {
                    Pawn pawn = (Pawn) piece;
                    pawn.setEnpassantable(false);
                }
            }
        }
    }

    public BoardSquare getSquare(int x, int y) {
        if ((x > 7 || x < 0) || (y > 7 || y < 0)) {
            return null;
        }
        return this.getChessBoard()[x].getRow()[y];
    }

    public Piece getPiece(int x, int y) {
        if (this.getSquare(x, y) == null) {
            return null;
        }
        return this.getSquare(x, y).getPiece();
    }

    public void placePiece(Piece piece, int x, int y) {
        this.getSquare(x, y).placePiece(piece);
    }

    public Piece removePiece(int x, int y) {
        return this.getSquare(x, y).removePiece();
    }

}
