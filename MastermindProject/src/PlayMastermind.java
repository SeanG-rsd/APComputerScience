import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
public class PlayMastermind
{

    private static int guessesUsed = 0;
    private static final int maxGuesses = 10;

    private static String code;


    public static void main(String[] args)
    {

        code = getRandomCode(4);

        Scanner console = new Scanner(System.in);
        MastermindGame game = new MastermindGame(code);

        while (guessesUsed < maxGuesses && !game.hasWon())
        {
            //game.printBoard();
            //game.makeGuess(getGuess(console));
            //guessesUsed++;
            guessesUsed = maxGuesses;
        }

        //game.solveAll();
        String guess = game.solver("1122");
        System.out.println(guess);
        //guess = game.solver(guess);
        //System.out.println(guess);
        //guess = game.solver(guess);


        if (game.hasWon())
        {
            System.out.println("YOU WIN");
        }
        else
        {
            System.out.println("YOU LOSE");
        }

    }

    public static String getGuess(Scanner console)
    {
        System.out.print("Make a guess : ");
        String guess = console.nextLine();
        char[] chars = guess.toCharArray();

        if (chars.length == 4)
        {
            for (char aChar : chars)
            {
                if (!Character.isDigit(aChar))
                {
                    return getGuess(console);
                }
                else if ((aChar - '0') > 6)
                {
                    return getGuess(console);
                }
            }

            return guess;
        }
        else
        {
            return getGuess(console);
        }
    }

    public static String getRandomCode(int length)
    {
        Random r = new Random();
        String code = "";

        for (int i = 0; i < length; ++i)
        {
            code += r.nextInt(6) + 1;
        }

        return code;
    }

}
