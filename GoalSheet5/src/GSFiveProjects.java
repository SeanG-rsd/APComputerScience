import java.util.*;
public class GSFiveProjects
{
    public static void main(String[] args)
    {
        hangman();
    }

    public static void outputSqaures() // BJP Ch 7 Proj 5
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

    public static void hangman()
    {
        char[] guessable = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] guessed = new char[26];

        String ANSWER = "jinx";

        Scanner console = new Scanner(System.in);



        System.out.println(Arrays.toString(guessed));

        printHangman(ANSWER, ANSWER, 0);

        guessed = getInput(console, guessed, guessable);
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
        System.out.println("    _________ ");
        System.out.println("    |       | ");
        System.out.println("    |       o ");
        System.out.println("    |      /|\\");
        System.out.println("    |      / \\");
        System.out.println("    |         ");

        printBoxEdge(answer.length());
        printBoxWord(answer.length(), found);
        printBoxEdge(answer.length());
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

}
