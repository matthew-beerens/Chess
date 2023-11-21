import domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChessBoardRowFactoryTest {
    @Test
    void testEmptyRow() {
        ChessBoardRow emptyRow = ChessBoardRowFactory.getEmptyRow();
        assertTrue(emptyRow.isEmpty());
    }

    @Test
    void testPawnRow() {
        BoardSquare[] pawnRow = ChessBoardRowFactory.getPawnRow().getRow();
        for (int i = 0; i < pawnRow.length; i++) {
            assertTrue(pawnRow[i].getPiece().getType() == PieceType.PAWN);
        }
    }

    @Test
    void testPieceRow() {
        BoardSquare[] pieceRow = ChessBoardRowFactory.getPieceRow().getRow();
        BoardSquare[] emptyRow = ChessBoardRowFactory.getEmptyRow().getRow();
        emptyRow[0].placePiece(new Rook());
        emptyRow[1].placePiece(new Knight());
        emptyRow[2].placePiece(new Bishop());
        emptyRow[3].placePiece(new Queen());
        emptyRow[4].placePiece(new King());
        emptyRow[5].placePiece(new Bishop());
        emptyRow[6].placePiece(new Knight());
        emptyRow[7].placePiece(new Rook());
        for (int i = 0; i < emptyRow.length; i++) {
            assertEquals(pieceRow[i].getPiece().getType(), emptyRow[i].getPiece().getType());
        }
    }

    @Test
    void testWhitePawnRow() {
        BoardSquare[] pawnRow = ChessBoardRowFactory.getPawnRow(PieceColor.WHITE).getRow();
        for (int i = 0; i < pawnRow.length; i++) {
            assertTrue(pawnRow[i].getPiece().getType() == PieceType.PAWN && pawnRow[i].getPiece().getColor() == PieceColor.WHITE);
        }
    }

    @Test
    void testBlackPawnRow() {
        BoardSquare[] pawnRow = ChessBoardRowFactory.getPawnRow(PieceColor.BLACK).getRow();
        for (int i = 0; i < pawnRow.length; i++) {
            assertTrue(pawnRow[i].getPiece().getType() == PieceType.PAWN && pawnRow[i].getPiece().getColor() == PieceColor.BLACK);
        }
    }

    @Test
    void testWhitePieceRow() {
        BoardSquare[] pieceRow = ChessBoardRowFactory.getPieceRow(PieceColor.WHITE).getRow();
        BoardSquare[] emptyRow = ChessBoardRowFactory.getEmptyRow().getRow();
        emptyRow[0].placePiece(new Rook(PieceColor.WHITE));
        emptyRow[1].placePiece(new Knight(PieceColor.WHITE));
        emptyRow[2].placePiece(new Bishop(PieceColor.WHITE));
        emptyRow[3].placePiece(new Queen(PieceColor.WHITE));
        emptyRow[4].placePiece(new King(PieceColor.WHITE));
        emptyRow[5].placePiece(new Bishop(PieceColor.WHITE));
        emptyRow[6].placePiece(new Knight(PieceColor.WHITE));
        emptyRow[7].placePiece(new Rook(PieceColor.WHITE));
        for (int i = 0; i < emptyRow.length; i++) {
            assertTrue(pieceRow[i].getPiece().getType() == emptyRow[i].getPiece().getType() && pieceRow[i].getPiece().getColor() == PieceColor.WHITE);
        }
    }

    @Test
    void testBlackPieceRow() {
        BoardSquare[] pieceRow = ChessBoardRowFactory.getPieceRow(PieceColor.BLACK).getRow();
        BoardSquare[] emptyRow = ChessBoardRowFactory.getEmptyRow().getRow();
        emptyRow[0].placePiece(new Rook(PieceColor.BLACK));
        emptyRow[1].placePiece(new Knight(PieceColor.BLACK));
        emptyRow[2].placePiece(new Bishop(PieceColor.BLACK));
        emptyRow[3].placePiece(new Queen(PieceColor.BLACK));
        emptyRow[4].placePiece(new King(PieceColor.BLACK));
        emptyRow[5].placePiece(new Bishop(PieceColor.BLACK));
        emptyRow[6].placePiece(new Knight(PieceColor.BLACK));
        emptyRow[7].placePiece(new Rook(PieceColor.BLACK));
        for (int i = 0; i < emptyRow.length; i++) {
            assertTrue(pieceRow[i].getPiece().getType() == emptyRow[i].getPiece().getType() && pieceRow[i].getPiece().getColor() == PieceColor.BLACK);
        }
    }
}
