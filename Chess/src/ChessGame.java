import java.lang.reflect.Array;
import java.util.*;

public class ChessGame
{

    private static Piece.PieceColor whoseTurn;

    public static void main(String[] args)
    {
        whoseTurn = Piece.PieceColor.WHITE;

        ChessBoard board = new ChessBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        ChessBoard tempBoard = new ChessBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
        board.PrintBoard();
        List<Move> movesForASide = board.GetAllMovesForAColor(whoseTurn, tempBoard);

        while (!movesForASide.isEmpty())
        {
            GetMove(board, tempBoard);
            System.out.println();
            board.PrintBoard();
            whoseTurn = whoseTurn == Piece.PieceColor.WHITE ? Piece.PieceColor.BLACK : Piece.PieceColor.WHITE;
            movesForASide = board.GetAllMovesForAColor(whoseTurn, tempBoard);
        }

        Piece.PieceColor whoWon = whoseTurn == Piece.PieceColor.WHITE ? Piece.PieceColor.BLACK : Piece.PieceColor.WHITE;
        System.out.println(whoWon + " WON");
    }

    public static void GetMove(ChessBoard board, ChessBoard tempBoard)
    {
        Piece[] pieces = board.GetBoard();

        Map<Integer, Piece> playerPieces = new HashMap<>();

        for (Piece p : pieces)
        {
            if (p != null) {
                if (p.pieceColor == whoseTurn) {
                    playerPieces.put(p.position, p);
                }
            }
        }

        Scanner console = new Scanner(System.in);
        System.out.println("What piece would you like to move?");
        int move = -1;
        do {
            System.out.println("Input an X Coordinate");
            int x = getPositionInput(console, 0, 7);
            System.out.println("Input an Y Coordinate");
            int y = getPositionInput(console, 0, 7);
            move = (7 - y) * 8 + x;
        } while (!playerPieces.containsKey(move));

        List<Move> possibleMoves = board.GetMoves(move, tempBoard, whoseTurn);
        List<Integer> possibleIndex = new ArrayList<>();
        for (Move m : possibleMoves)
        {
            possibleIndex.add(m.getPosition());
        }
        System.out.println();
        System.out.println(possibleMoves);
        System.out.println("Where would you like to move your " + playerPieces.get(move).GetName() + "?");
        int index = -1;

        do {
            System.out.println("Input an X Coordinate");
            int x = getPositionInput(console, 0, 7);
            System.out.println("Input an Y Coordinate");
            int y = getPositionInput(console, 0, 7);
            index = (7 - y) * 8 + x;
        } while (!possibleIndex.contains(index));

        board.MakeMove(possibleMoves.get(possibleIndex.indexOf(index)), false);
        tempBoard.MakeMove(possibleMoves.get(possibleIndex.indexOf(index)), true);
    }

    public static int getPositionInput(Scanner console, int min, int max)
    {
        int output = -1;
        do {
            System.out.print(">> ");
            output = console.nextInt();
        } while (!(output >= min && output <= max));

        return output;
    }
}
