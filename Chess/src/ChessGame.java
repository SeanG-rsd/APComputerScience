import java.lang.reflect.Array;
import java.util.*;

public class ChessGame
{

    

    public static void main(String[] args)
    {
        ChessBoard board = new ChessBoard("r1bqkbnr/ppp2ppp/2n1p3/1B1p2Q1/3PP3/1P6/P1P2PPP/RNB1K1NR");
        board.PrintBoard();
        board.GetMoves(18);
        board.GetMoves(13);
    }


}
