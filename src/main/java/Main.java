import domain.ChessBoard;
import domain.Pawn;
import domain.PieceColor;
import domain.PieceType;
import javafx.application.Application;
import ui.MainUi;

public class Main {
    public static void main(String[] args) {
        //Application.launch(MainUi.class);
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.initialize(PieceColor.WHITE, PieceColor.BLACK);
        System.out.println(chessBoard);
        chessBoard.movePiece(chessBoard.getChessBoard()[6].getRow()[0], chessBoard.getChessBoard()[4].getRow()[0]);
        System.out.println(chessBoard);;
    }
}
