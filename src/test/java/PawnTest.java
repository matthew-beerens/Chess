import domain.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
