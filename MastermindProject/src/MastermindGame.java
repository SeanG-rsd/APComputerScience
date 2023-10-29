import java.util.*;
public class MastermindGame
{
    private static List<String> guesses;
    private static String code;
    private static final String noGuess = "XXXX";
    private static final int numGuesses = 10;

    private static MastermindPins pinCalc;

    public MastermindGame(String code)
    {
        guesses = new LinkedList<>();
        pinCalc = new MastermindPins();

        this.code = code;

        for (int i = 0; i < numGuesses; ++i)
        {
            guesses.add(noGuess);
        }
    }
    public void printBoard()
    {
        printAnswerBox();

        for (int i = 0; i < numGuesses; ++i)
        {
            printLine(guesses.get(i));
        }

        System.out.println("---------------------------");
    }

    public static void printLine(String guess)
    {
        char[] guessChars = guess.toCharArray();

        String white = "  ";
        String black = "  ";

        if (!guess.equals(noGuess))
        {
            int[] pegs = pinCalc.calculatePegs(guess, code); // [0] is white, [1] is black

            white = pegs[0] + "w";
            black = pegs[1] + "b";
        }

        System.out.println("---------------------------");
        System.out.println("|              |          |");
        System.out.println("|  " + guessChars[0] + "  " + guessChars[1] + "  " + guessChars[2] + "  " + guessChars[3] + "  |  " + white + "  " + black + "  |");
        System.out.println("|              |          |");
    }

    public static void printAnswerBox()
    {
        System.out.println("---------------------------");
        System.out.println("|    ?????     |**********|");
        System.out.println("|    ?????     |**********|");
        System.out.println("|    ?????     |**********|");
    }

    public void makeGuess(String guess)
    {
        for (int i = guesses.size() - 1; i >= 0; i--)
        {
            if (guesses.get(i).compareTo(noGuess) == 0)
            {
                guesses.set(i, guess);
                return;
            }
        }
    }

    public boolean hasWon()
    {
        for (int i = guesses.size() - 1; i >= 0; --i)
        {
            if (guesses.get(i).equals(code))
            {
                return true;
            }
        }

        return false;
    }


}
