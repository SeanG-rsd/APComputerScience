import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
Name : Sean Gutmann
Project : Hangman
 */

public class HangmanProject
{
    public static void main(String[] args) throws FileNotFoundException
    {
        hangman(pickRandomAnswer());
    }

    public static String pickRandomAnswer() throws FileNotFoundException // picks a random answer from a file
    {
        Scanner file = new Scanner(new File("/Users/gutmannse/Desktop/gutmannsean/APComputerScience/Semester1Proj1/src/hangmanInput"));

        List<String> words = new LinkedList<>();

        while (file.hasNext())
        {
            words.add(file.nextLine());
        }

        Random r = new Random();
        int index = r.nextInt(words.size());

        return words.get(index);
    }

    public static boolean wantsToPlay() // checks if the user wants to keep playing
    {
        String input = "";
        Scanner console = new Scanner(System.in);

        while (true)
        {
            System.out.print("\nWould you like to play hangman?\nEnter your answer (y or n) : ");
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

    public static void hangman(String answer) // the main loop
    {
        while (wantsToPlay())
        {
            char[] guessable = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
            char[] guessed = new char[26];

            int numWrong = 0;
            int MAX_WRONG = 6;

            String ANSWER = answer;
            String found = "";
            found = calculateFound(ANSWER, guessed);

            Scanner console = new Scanner(System.in);

            while (!hasWonOrLost(ANSWER, found, numWrong, MAX_WRONG)) // game loop until win or lose
            {
                printHangman(ANSWER, found, numWrong);

                guessed = getInput(console, guessed, guessable);

                found = calculateFound(ANSWER, guessed);

                numWrong = getWrong(ANSWER, guessed);
            }

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();


            if (numWrong >= MAX_WRONG)
            {
                printHangman(ANSWER, ANSWER, numWrong);
                System.out.println("YOU LOST.");
            }

            if (ANSWER.equals(found))
            {
                printHangman(ANSWER, ANSWER, numWrong);
                System.out.println("YOU WON!!");

            }
        }

        System.out.println("\n\n\n\nThanks for playing!!");
    }

    public static boolean hasWonOrLost(String ANSWER, String found, int wrongGuesses, int maxWrong) // checks if the player has won or lost
    {
        if (wrongGuesses >= maxWrong || ANSWER.equals(found))
        {
            return true;
        }

        return false;
    }

    public static char[] getInput(Scanner console, char[] guessed, char[] base) // gets input from the player
    {
        System.out.println();
        System.out.print("Guessed : ");

        for (char c : guessed)
        {
            if (c != '\u0000')
            {
                System.out.print(c + ", ");
            }
        }

        System.out.println();

        System.out.print("Enter a guess : ");
        char[] input = console.nextLine().toCharArray();

        char[] guessable = calculateGuessable(base, guessed);

        for (char c : input)
        {
            for (char g : guessable)
            {
                if (c == g) // if it is guessable
                {
                    for (int i = 0; i < guessed.length; ++i)
                    {
                        if (guessed[i] == '\u0000')
                        {

                            guessed[i] = c;
                            return guessed;
                        }
                    }
                }
            }
        }


        return guessed;
    }

    public static char[] calculateGuessable(char[] base, char[] guessed) // calculate what has been guessed and what letters are possible for the answer
    {
        char[] guessable = new char[26];

        for (int i = 0; i < base.length; ++i)
        {
            boolean hasGuessed = false;

            for (char c : guessed)
            {
                if (c == base[i])
                {
                    hasGuessed = true;
                    break;
                }
            }

            if (!hasGuessed)
            {
                for (int j = 0; j < guessable.length; ++j)
                {
                    if (guessable[j] == '\u0000')
                    {
                        guessable[j] = base[i];
                        break;
                    }
                }
            }
        }

        return guessable;
    }

    public static void printHangman(String answer, String found, int wrongGuesses) // prints out the board
    {
        printMan(wrongGuesses);

        printBoxEdge(answer.length());
        printBoxWord(answer.length(), found);
        printBoxEdge(answer.length());
    }

    public static void printMan(int wrongGuesses) // prints out the possible man
    {
        if (wrongGuesses == 6)
        {
            System.out.println("    _________ ");
            System.out.println("    |       | ");
            System.out.println("    |       o ");
            System.out.println("    |      /|\\");
            System.out.println("    |      / \\");
            System.out.println("    |         ");
        }
        else if (wrongGuesses == 5)
        {
            System.out.println("    _________ ");
            System.out.println("    |       | ");
            System.out.println("    |       o ");
            System.out.println("    |      /|\\");
            System.out.println("    |      /  ");
            System.out.println("    |         ");
        }
        else if (wrongGuesses == 4)
        {
            System.out.println("    _________ ");
            System.out.println("    |       | ");
            System.out.println("    |       o ");
            System.out.println("    |      /|\\");
            System.out.println("    |         ");
            System.out.println("    |         ");
        }
        else if (wrongGuesses == 3)
        {
            System.out.println("    _________ ");
            System.out.println("    |       | ");
            System.out.println("    |       o ");
            System.out.println("    |      /| ");
            System.out.println("    |         ");
            System.out.println("    |         ");
        }
        else if (wrongGuesses == 2)
        {
            System.out.println("    _________ ");
            System.out.println("    |       | ");
            System.out.println("    |       o ");
            System.out.println("    |       | ");
            System.out.println("    |         ");
            System.out.println("    |         ");
        }
        else if (wrongGuesses == 1)
        {
            System.out.println("    _________ ");
            System.out.println("    |       | ");
            System.out.println("    |       o ");
            System.out.println("    |         ");
            System.out.println("    |         ");
            System.out.println("    |         ");
        }
        else if (wrongGuesses == 0)
        {
            System.out.println("    _________ ");
            System.out.println("    |       | ");
            System.out.println("    |         ");
            System.out.println("    |         ");
            System.out.println("    |         ");
            System.out.println("    |         ");
        }
    }

    public static void printBoxEdge(int length) // prints the box
    {
        for (int i = 0; i < ((length + 1) * 3 + 1); ++i)
        {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printBoxWord(int length, String found) // prints the word for the board
    {
        System.out.print("|  ");
        found = found.toUpperCase();
        for (int i = 0; i < length; ++i)
        {
            System.out.print(found.charAt(i) + "  ");
        }
        System.out.println("|");
    }

    public static String calculateFound(String ANSWER, char[] guessed) // calculate the shown word for the box
    {
        String found = "";

        for (int i = 0; i < ANSWER.length(); ++i)
        {
            char add = '_';

            for (char c : guessed)
            {
                if (c == ANSWER.charAt(i))
                {
                    add = c;
                }
            }

            found += add;
        }

        return found;
    }

    public static int getWrong(String ANSWER, char[] guessed) // check how many the player has wrong
    {
        int wrong = 0;

        for (char c : guessed)
        {
            if (c != '\u0000')
            {
                boolean isInAnswer = false;

                for (int i = 0; i < ANSWER.length(); ++i)
                {
                    if (c == ANSWER.charAt(i))
                    {
                        isInAnswer = true;
                    }


                }

                if (!isInAnswer) {
                    wrong++;
                }
            }

        }

        return wrong;
    }
}
