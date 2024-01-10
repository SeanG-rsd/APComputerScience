import java.util.*;

public class ChessGame
{
    static Piece[] board = new Piece[64];

    

    public static void main(String[] args)
    {
        InitializeBoard("ad");
    }

    public static void InitializeBoard(String coded)
    {
        Piece newPiece = new Rook(Piece.PieceColor.WHITE, 8);
        board[newPiece.getPosition()] = newPiece;
        Piece secondPiece = new Pawn(Piece.PieceColor.BLACK, 32);
        board[secondPiece.getPosition()] = secondPiece;
        Piece knight = new Knight(Piece.PieceColor.WHITE, 26);
        board[knight.getPosition()] = knight;
        List<Move> moves = new ArrayList<>();
        newPiece.GetMoves(board, moves);
        System.out.println(moves);
        //secondPiece.GetMoves(board, moves);
        //System.out.println(moves);
        //knight.GetMoves(board, moves);
        //System.out.println(moves);
    }
}
