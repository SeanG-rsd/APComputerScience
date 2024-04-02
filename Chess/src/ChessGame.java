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
            if (whoseTurn == white)
            {
                legalMoves.clear();
                decodedMoves.clear();
                newBot.PrintBoard();
                legalMoves = newBot.GetLegalMoves();
                System.out.println(legalMoves.size());
                for (int legalMove : legalMoves) {
                    String visual = newBot.MoveToString(legalMove);

                    System.out.print(visual + ", ");
                    decodedMoves.add(visual);
                }

                move = GetMove(decodedMoves);
                if (!move.equals("u")) {
                    newBot.MakeMove(legalMoves.get(decodedMoves.indexOf(move)));
                }
                else
                {
                    newBot.UndoMove();
                }
                whoseTurn = black;
            }
            else
            {
                newBot.MakeBestMove();
                whoseTurn = white;
            }
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
            if (answer.equals(("u"))) break;
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
                if (Character.isUpperCase(array[i]) && i != 4)
                {
                    array[i] = (char)(array[i] + 32);
                }
            }
            output += array[i];
        }
        return output;
    }
}

