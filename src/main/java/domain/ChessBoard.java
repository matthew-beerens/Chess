package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.abs;

public class ChessBoard {
    private ChessBoardRow[] chessBoard;
    private ArrayList<BoardSquare> blackMoves;
    private ArrayList<BoardSquare> whiteMoves;
    private ArrayList<BoardSquare> allMoves;
    public ChessBoard() {
        this.chessBoard = new ChessBoardRow[8];
        this.blackMoves = new ArrayList<>();
        this.whiteMoves = new ArrayList<>();
        this.allMoves = new ArrayList<>();
        for (int i = 0; i < this.chessBoard.length; i++) {
            this.chessBoard[i] = new ChessBoardRow();
        }
        this.setDangerousMoves();
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
        // check for checkmate white
        if (this.checkMate(PieceColor.WHITE)) {
            System.out.println("checkmate - white lose");
            return;
        }
        // check for checkmate black
        if (this.checkMate(PieceColor.BLACK)) {
            System.out.println("checkmate - black lose");
            return;
        }
        // get moves
        List<BoardSquare> moves = source.getPiece().getMoves(this, source.getPosition());
        // prevent unsafe move
        if (this.isDangerousMove(source, destination)) {
            return;
        }
        // valid move check
        if (!moves.contains(destination)) {
            return;
        }
        // castle logic
        if (checkCastle(source, destination) && !this.kingChecked()) {
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
        this.setDangerousMoves();
    }

    public boolean checkMate(PieceColor color) {
        for (ChessBoardRow cbr : this.getChessBoard()) {
            for (BoardSquare bs : cbr.getRow()) {
                if (!bs.getPiece().getColor().equals(color)) {
                    continue;
                }
                List<BoardSquare> moves = bs.getPiece().getMoves(this, bs.getPosition());
                for (BoardSquare move : moves) {
                    if (!this.isDangerousMove(bs, move)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkCastle(BoardSquare source, BoardSquare destination) {
        return source.getPiece().getType().equals(PieceType.KING) && destination.getPiece().getType().equals(PieceType.ROOK);
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
        return (source.getPiece().getType().equals(PieceType.PAWN)
                && ((source.getPosition().getX() - destination.getPosition().getX() == 2)
                || (source.getPosition().getX() - destination.getPosition().getX() == -2)));
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

    public boolean kingChecked() {
        this.allMoves.clear();
        this.allMoves.addAll(this.blackMoves);
        this.allMoves.addAll(this.whiteMoves);
        for (BoardSquare bs : this.allMoves) {
            if (bs.getPiece().getType().equals(PieceType.KING)) {
                return true;
            }
        }
        return false;
    }

    public PieceColor getChecked() {
        this.allMoves.clear();
        this.allMoves.addAll(this.blackMoves);
        this.allMoves.addAll(this.whiteMoves);
        for (BoardSquare bs : this.allMoves) {
            if (bs.getPiece().getType().equals(PieceType.KING)) {
                return bs.getPiece().getColor();
            }
        }
        return null;
    }

    public boolean isDangerousMove(BoardSquare source, BoardSquare destination) {
        boolean verdict = false;
        Piece srcPiece = this.removePiece(source.getPosition().getX(), source.getPosition().getY());
        Piece dstPiece = this.removePiece(destination.getPosition().getX(), destination.getPosition().getY());
        this.placePiece(srcPiece, destination.getPosition().getX(), destination.getPosition().getY());
        this.setDangerousMoves();

        if (this.getChecked() == null) {
            verdict = false;
        }

        else if (this.getChecked().equals(srcPiece.getColor())) {
            verdict = true;
        }

        this.removePiece(destination.getPosition().getX(), destination.getPosition().getY());
        this.placePiece(dstPiece, destination.getPosition().getX(), destination.getPosition().getY());
        this.placePiece(srcPiece, source.getPosition().getX(), source.getPosition().getY());
        this.setDangerousMoves();
        return verdict;
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

    public ArrayList<BoardSquare> getBlackMoves() {
        return this.blackMoves;
    }

    public ArrayList<BoardSquare> getWhiteMoves() {
        return this.whiteMoves;
    }

    public void placePiece(Piece piece, int x, int y) {
        this.getSquare(x, y).placePiece(piece);
    }

    public Piece removePiece(int x, int y) {
        return this.getSquare(x, y).removePiece();
    }

    public void setDangerousMoves() {
        this.blackMoves.clear();
        this.whiteMoves.clear();
        for (ChessBoardRow bs : this.getChessBoard()) {
            for (BoardSquare square: bs.getRow()) {
                Piece piece = square.getPiece();
                if (piece.getType().equals(PieceType.NULL)) {
                    continue;
                }
                if (piece.getColor().equals(PieceColor.WHITE)) {
                    this.whiteMoves.addAll(square.getPiece().getMoves(this, square.getPosition()));
                } else {
                    this.blackMoves.addAll(square.getPiece().getMoves(this, square.getPosition()));
                }
            }
        }
    }

}
