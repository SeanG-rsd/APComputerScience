import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

/*
Name : Sean Gutmann
Project : Mastermind Game
 */
public class PlayMastermind
{

    private static int guessesUsed = 0;
    private static final int maxGuesses = 10;

    private static String code;


    public static boolean wantsToPlay() // checks if the user wants to keep playing
    {
        String input = "";
        Scanner console = new Scanner(System.in);

        while (true)
        {
            System.out.print("\nWould you like to play mastermind?\nEnter your answer (y or n) : ");
            input = console.nextLine();
            char[] chars = input.toUpperCase().toCharArray();

            if (chars[0] == 'Y')
            {
                return true;
            }
            else if (chars[0] == 'N')
            {
                return false;
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);

        MastermindGame g = new MastermindGame(code);

        g.solveAll(); // for knuth algorithm
        //System.out.println("1122");
        //String guess = g.solver("1122");
        //System.out.println(guess);
        //guess = g.solver(guess);
        //System.out.println(guess);
        //guess = g.solver(guess);
        //System.out.println(guess);

        while (wantsToPlay() && false)
        {
            guessesUsed = 0;
            code = getRandomCode(4);
            MastermindGame game = new MastermindGame(code);

            while (guessesUsed < maxGuesses && !game.hasWon()) // main game loop
            {
                //game.printBoard();
                //game.makeGuess(getGuess(console));
                //guessesUsed++;
                guessesUsed = maxGuesses;
            }

            game.solveAll(); // for knuth algorithm
            //System.out.println("1122");
            //String guess = game.solver("1122");
            //System.out.println(guess);
            //guess = game.solver(guess);
            //System.out.println(guess);
            //guess = game.solver(guess);
            //System.out.println(guess);


            if (game.hasWon())
            {
                System.out.println("YOU WIN");
            }
            else
            {
                System.out.println("YOU LOSE");
                System.out.println("Answer was : " + code);
            }
        }

    }

    public static String getGuess(Scanner console) // gets a guess from a player and loops until they enter a valid guess
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

    public static String getRandomCode(int length) // gets a random code to play with
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
