import domain.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {
    private Pawn pawn = new Pawn();

    @Test
    void createNewPawnPiece() {
        assertEquals(this.pawn.getType(), PieceType.PAWN);
    }

    @Test
    void pawnToString() {
        assertEquals(this.pawn.toString(), PieceType.PAWN.toString());
    }

    @Test
    void pawnGetMovesWhite() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize(PieceColor.WHITE, PieceColor.BLACK);
        Pawn whitePawn = (Pawn) chessBoard.getChessBoard()[6].getRow()[0].getPiece();
        List<BoardSquare> moves = whitePawn.getMoves(chessBoard, chessBoard.getChessBoard()[6].getRow()[0].getPosition());
        BoardSquare bs0 = new BoardSquare();
        BoardSquare bs1 = new BoardSquare();
        bs0.getPosition().setPosition(5, 0);
        bs1.getPosition().setPosition(4, 0);
        assertTrue(moves.contains(bs0) && moves.contains(bs1));
    }

    @Test
    void pawnGetMovesBlack() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize(PieceColor.WHITE, PieceColor.BLACK);
        Pawn blackPawn = (Pawn) chessBoard.getChessBoard()[1].getRow()[0].getPiece();
        List<BoardSquare> moves = blackPawn.getMoves(chessBoard, chessBoard.getChessBoard()[1].getRow()[0].getPosition());
        BoardSquare bs0 = new BoardSquare();
        BoardSquare bs1 = new BoardSquare();
        bs0.getPosition().setPosition(2, 0);
        bs1.getPosition().setPosition(3, 0);
        assertTrue(moves.contains(bs0) && moves.contains(bs1));
    }

    @Test
    void PawnGetMovesBlackEdge() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize(PieceColor.WHITE, PieceColor.BLACK);
        chessBoard.placePiece(new Pawn(PieceColor.BLACK),7, 0);
        List<BoardSquare> moves = chessBoard.getPiece(7, 0).getMoves(chessBoard, chessBoard.getSquare(7, 0).getPosition());
        assertTrue(moves.isEmpty());
    }

    @Test
    void PawnGetMovesWhiteEdge() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize(PieceColor.WHITE, PieceColor.BLACK);
        chessBoard.placePiece(new Pawn(PieceColor.WHITE),0, 0);
        List<BoardSquare> moves = chessBoard.getPiece(0, 0).getMoves(chessBoard, chessBoard.getSquare(0, 0).getPosition());
        assertTrue(moves.isEmpty());
    }

    @Test
    void whitePawnGetAllMoves() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        cb.placePiece(new Pawn(PieceColor.BLACK), 5, 0);
        cb.placePiece(new Pawn(PieceColor.BLACK), 5, 2);
        BoardSquare bs0 = new BoardSquare();
        BoardSquare bs1 = new BoardSquare();
        BoardSquare bs2 = new BoardSquare();
        BoardSquare bs3 = new BoardSquare();
        bs0.getPosition().setPosition(5, 0);
        bs1.getPosition().setPosition(5, 1);
        bs2.getPosition().setPosition(5, 2);
        bs3.getPosition().setPosition(4, 1);
        List<BoardSquare> moves = cb.getPiece(6, 1).getMoves(cb, cb.getSquare(6, 1).getPosition());
        assertTrue(moves.contains(bs0));
        assertTrue(moves.contains(bs1));
        assertTrue(moves.contains(bs2));
        assertTrue(moves.contains(bs3));
    }

    @Test
    void blackPawnGetAllMoves() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        cb.placePiece(new Pawn(PieceColor.WHITE), 2, 0);
        cb.placePiece(new Pawn(PieceColor.WHITE), 2, 2);
        BoardSquare bs0 = new BoardSquare();
        BoardSquare bs1 = new BoardSquare();
        BoardSquare bs2 = new BoardSquare();
        BoardSquare bs3 = new BoardSquare();
        bs0.getPosition().setPosition(2, 0);
        bs1.getPosition().setPosition(2, 1);
        bs2.getPosition().setPosition(2, 2);
        bs3.getPosition().setPosition(3, 1);
        List<BoardSquare> moves = cb.getPiece(1, 1).getMoves(cb, cb.getSquare(1, 1).getPosition());
        assertTrue(moves.contains(bs0));
        assertTrue(moves.contains(bs1));
        assertTrue(moves.contains(bs2));
        assertTrue(moves.contains(bs3));
    }

    @Test
    void notFirstMoveWhite() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        cb.placePiece(new Pawn(PieceColor.BLACK), 5, 0);
        cb.placePiece(new Pawn(PieceColor.BLACK), 5, 2);
        Pawn pawn = (Pawn )cb.getPiece(6, 1);
        pawn.setFirstMove(false);
        List<BoardSquare> moves = cb.getPiece(6, 1).getMoves(cb, cb.getSquare(6, 1).getPosition());
        assertEquals(moves.size(), 3);
    }

    @Test
    void notFirstMoveBlack() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        cb.placePiece(new Pawn(PieceColor.WHITE), 2, 0);
        cb.placePiece(new Pawn(PieceColor.WHITE), 2, 2);
        Pawn pawn = (Pawn )cb.getPiece(1, 1);
        pawn.setFirstMove(false);
        List<BoardSquare> moves = cb.getPiece(1, 1).getMoves(cb, cb.getSquare(1, 1).getPosition());
        assertEquals(moves.size(), 3);
    }

    @Test
    void pawnIsWhite() {
        Pawn whitePawn = new Pawn(PieceColor.WHITE);
        assertEquals(whitePawn.getColor(), PieceColor.WHITE);
    }

    @Test
    void pawnIsBlack() {
        Pawn blackPawn = new Pawn(PieceColor.BLACK);
        assertEquals(blackPawn.getColor(), PieceColor.BLACK);
    }

    @Test
    void pawnIsNotAColor() {
        Pawn nullPawn = new Pawn();
        assertEquals(nullPawn.getColor(), PieceColor.NULL);
    }

    @Test
    void singleMoveAfterFirstMove() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        cb.movePiece(cb.getSquare(6, 2), cb.getSquare(4, 2));
        Pawn pawn = (Pawn) cb.getPiece(4, 2);
        assertEquals(1, pawn.getMoves(cb, cb.getSquare(4,2).getPosition()).size());
    }

    @Test
    void takeEnpassantableMove() {
        ChessBoard cb = new ChessBoard();
        cb.initialize(PieceColor.WHITE, PieceColor.BLACK);
        cb.placePiece(new Pawn(PieceColor.WHITE), 3, 4);

        Pawn pawn = new Pawn(PieceColor.BLACK);
        pawn.setEnpassantable(true);
        cb.placePiece(pawn, 3, 5);

        List<BoardSquare> moves = cb.getPiece(3, 4).getMoves(cb, cb.getSquare(3, 4).getPosition());
        assertEquals(2, moves.size());
    }

}
