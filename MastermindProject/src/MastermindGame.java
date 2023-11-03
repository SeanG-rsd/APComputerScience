import java.util.*;
public class MastermindGame
{
    private static List<String> guesses;
    private static String code;
    private static final String noGuess = "XXXX";
    private static final int numGuesses = 10;

    private static MastermindPins pinCalc;

    private static List<String> allCodes;
    private static List<String> availableCodes;

    public MastermindGame(String code)
    {
        allCodes = new LinkedList<>();
        GetAllCodes();
        availableCodes = new LinkedList<>(allCodes);

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

    public String solver(String guess)
    {
        MastermindSolver solver = new MastermindSolver();
        int[] pegs = pinCalc.calculatePegs("3632", guess);
        return solver.getNextGuess(pegs[0], pegs[1], guess, availableCodes, allCodes, 0);
    }

    public void solveAll()
    {
        MastermindSolver solver = new MastermindSolver();
        int correct = 0;
        int tested = 0;

        float guessesUsed = 0;
        List<Integer> guessesPer = new LinkedList<>();

        for (String code : allCodes)
        {
            System.out.println(code);
            availableCodes = new LinkedList<>(allCodes);
            String guess = "1122";
            int guesses = 0;

            while (guesses < 20)
            {
                int[] pegs = pinCalc.calculatePegs(code, guess);
                guesses++;
                guessesUsed++;
                if (pegs[0] == 4)
                {
                    break;
                }
                guess = solver.getNextGuess(pegs[0], pegs[1], guess, availableCodes, allCodes, guesses + 1);
            }

            if (guesses <= 10)
            {
                correct++;
            }

            guessesPer.add(guesses);

            tested++;
        }

        Collections.sort(guessesPer);
        System.out.println(correct + "/" + tested);
        System.out.println(guessesUsed / tested);
        System.out.println(guessesPer.get(guessesPer.size() - 1));
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

    private static void GetAllCodes()
    {
        allCodes.clear();

        for (int first = 1; first <= 6; ++first)
        {
            for (int second = 1; second <= 6; ++second)
            {
                for (int third = 1; third <= 6; ++third)
                {
                    for (int fourth = 1; fourth <= 6; ++fourth)
                    {
                        String add = String.valueOf(first);
                        add += second;
                        add += third;
                        add += fourth;

                        allCodes.add(add);
                    }
                }
            }
        }
    }
}
