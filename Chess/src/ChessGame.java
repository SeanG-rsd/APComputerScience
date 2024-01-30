import com.sun.tools.jconsole.JConsoleContext;

import java.lang.reflect.Array;
import java.util.*;

public class ChessGame
{

    static int white = 0;
    static int black = 1;
    private static int whoseTurn;

    public static void main(String[] args)
    {
        whoseTurn = white;

        CheeseBot newBot = new CheeseBot();
        List<Integer> legalMoves = new ArrayList<>();
        List<String> decodedMoves = new ArrayList<>();
        String move = "";

        while (true)
        {
            legalMoves.clear();
            decodedMoves.clear();
            newBot.PrintBoard();
            legalMoves = newBot.GetLegalMoves();
            for (int legalMove : legalMoves)
            {
                String visual = newBot.MoveToString(legalMove);
                System.out.print(visual + ", ");
                decodedMoves.add(visual);
            }

            move = GetMove(decodedMoves);
            newBot.MakeMove(legalMoves.get(decodedMoves.indexOf(move)));
            System.out.println();
        }
    }

    public static String GetMove(List<String> possibleMoves)
    {
        System.out.println("\nWhat move would you like to make?");
        String answer = "";
        Scanner console = new Scanner(System.in);
        do {
            System.out.print(">> ");
            answer = console.next();
            answer = ToLegalMove(answer);

        } while (!possibleMoves.contains(answer));

        return answer;
    }

    public static String ToLegalMove(String choice)
    {
        char[] array = choice.toCharArray();
        String output = "";
        for (int i = 0; i < array.length; ++i)
        {
            if (!Character.isDigit(array[i]))
            {
                if (Character.isUpperCase(array[i]))
                {
                    array[i] = (char)(array[i] + 32);
                }
            }
            output += array[i];
        }
        return output;
    }

    /*public static void GetMove(ChessBoard board, ChessBoard tempBoard)
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

        board.MakeMove(possibleMoves.get(possibleIndex.indexOf(index)));
        tempBoard.MakeMove(possibleMoves.get(possibleIndex.indexOf(index)));

        //board.UndoMove(possibleMoves.get(possibleIndex.indexOf(index)));
        //tempBoard.UndoMove(possibleMoves.get(possibleIndex.indexOf(index)));
    }

    public static int getPositionInput(Scanner console, int min, int max)
    {
        int output = -1;
        do {
            System.out.print(">> ");
            output = console.nextInt();
        } while (!(output >= min && output <= max));

        return output;
    }*/
}
