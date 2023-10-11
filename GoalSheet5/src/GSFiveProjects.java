import java.util.*;
import java.io.*;
public class GSFiveProjects
{
    public static void main(String[] args) throws FileNotFoundException
    {
        hangman(pickRandomAnswer());
    }

    public static String pickRandomAnswer() throws FileNotFoundException
    {
        Scanner file = new Scanner(new File("/Users/gutmannse/Desktop/gutmannsean/APComputerScience/GoalSheet5/src/hangmanInput"));

        List<String> words = new LinkedList<>();
        while (file.hasNext())
        {
            words.add(file.nextLine());
        }

        Random r = new Random();
        int index = r.nextInt(words.size());

        return words.get(index);
    }

    public static void outputSquares() // BJP Ch 7 Proj 5
    {
        char[][] board = new char[3][];
        board[0] = new char[] {'x', 'o', '*'};
        board[1] = new char[] {'*', 'x', 'o'};
        board[2] = new char[] {'*', 'o', 'x'};

        System.out.println("-------------");
        for (int i = 0; i < board.length; ++i)
        {
            System.out.print("|");
            for (int ii = 0; ii < board[i].length; ++ii)
            {
                System.out.print(" " + board[i][ii] + " |");
            }

            System.out.println();
            System.out.println("-------------");
        }
    }

    public static void hangman(String answer)
    {
        char[] guessable = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] guessed = new char[26];

        int numWrong = 0;
        int MAX_WRONG = 6;

        String ANSWER = answer;
        String found = "";
        found = calculateFound(ANSWER, guessed);

        Scanner console = new Scanner(System.in);
        while (!hasWonOrLost(ANSWER, found, numWrong, MAX_WRONG))
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
            System.out.println("YOU LOST.");
        }

        if (ANSWER.equals(found))
        {
            printHangman(ANSWER, ANSWER, numWrong);
            System.out.println("YOU WON!!");

        }
    }

    public static boolean hasWonOrLost(String ANSWER, String found, int wrongGuesses, int maxWrong)
    {
        if (wrongGuesses >= maxWrong || ANSWER.equals(found))
        {

            return true;
        }

        return false;
    }

    public static char[] getInput(Scanner console, char[] guessed, char[] base)
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

    public static char[] calculateGuessable(char[] base, char[] guessed)
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

    public static void printHangman(String answer, String found, int wrongGuesses)
    {
        printMan(wrongGuesses);

        printBoxEdge(answer.length());
        printBoxWord(answer.length(), found);
        printBoxEdge(answer.length());
    }

    public static void printMan(int wrongGuesses)
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

    public static void printBoxEdge(int length)
    {
        for (int i = 0; i < ((length + 1) * 3 + 1); ++i)
        {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printBoxWord(int length, String found)
    {
        System.out.print("|  ");
        found = found.toUpperCase();
        for (int i = 0; i < length; ++i)
        {
            System.out.print(found.charAt(i) + "  ");
        }
        System.out.println("|");
    }

    public static String calculateFound(String ANSWER, char[] guessed)
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

    public static int getWrong(String ANSWER, char[] guessed)
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
