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

        Scanner console = new Scanner(System.in);

        char input = getInput(console, guessed, guessable);

        System.out.println(input);
    }

    public static char getInput(Scanner console, char[] guessed, char[] base)
    {
        System.out.println();
        System.out.print("Guessed : ");

        for (char c : guessed)
        {
            System.out.print(c + ", ");
        }
        System.out.println();

        System.out.print("Enter a guess : ");
        char[] input = console.nextLine().toCharArray();

        char[] guessable = calculateGuessable(base, guessed);

        for (char c : input)
        {
            for (char g : guessable)
            {
                if (g == c)
                {
                    return c;
                }
            }
        }


        return '0';
    }

    public static char[] calculateGuessable(char[] base, char[] guessed)
    {
        char[] guessable = new char[26];

        for (char c : base)
        {
            boolean has = false;
            for (char g : guessed)
            {
                if (g == c)
                {
                    has = true;
                }
            }

            if (!has)
            {
                for (char g : guessable)
                {
                    if (g == '0')
                    {
                        g = c;
                        break;
                    }
                }
            }
        }

        return guessable;
    }
}
