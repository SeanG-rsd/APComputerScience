import java.util.*;

public class MinesweeperGame
{
    private static final int SIZE_X = 10;
    private static final int SIZE_Y = 10;
    private static final int MINE_COUNT = 10;

    public static void main(String[] args)
    {
        int flagsPlaced = 0;
        Scanner console = new Scanner(System.in);

        BoardGenerator boardGenerator = new BoardGenerator(SIZE_X, SIZE_Y,MINE_COUNT);

        while (!boardGenerator.hasLost)
        {
            boardGenerator.print();
            char guess = GetGuess(flagsPlaced, console, boardGenerator);
            if (guess == 'f')
            {
                flagsPlaced = boardGenerator.flagCount;
            }
        }
    }

    public static char GetGuess(int flagsPlaced, Scanner console, BoardGenerator board)
    {
        System.out.println("You have " + (MINE_COUNT - flagsPlaced) + " flags left.");
        System.out.println("Would you like to guess (g) or put a flag somewhere (f)?");
        char answer = GetResponse(new LinkedList<Character>(List.of('g', 'f')), console);
        System.out.println("Where would you like to guess?");
        int spotX = -1;
        while (spotX == -1)
        {
            System.out.print("X : ");
            int check = console.nextInt();
            if (check > 0 && check <= SIZE_X)
            {
                spotX = check;
            }
        }

        int spotY = -1;
        while (spotY == -1)
        {
            System.out.print("Y : ");
            int check = console.nextInt();
            if (check > 0 && check <= SIZE_X)
            {
                spotY = check;
            }
        }

        if (answer == 'f')
        {
            board.flagSpot(spotY - 1, spotX - 1);
        }
        else if (answer == 'g')
        {
            board.GuessSpot(spotY - 1, spotX - 1);
        }

        return answer;
    }

    public static char GetResponse(List<Character> answers, Scanner console)
    {
        char output = ' ';
        while (!answers.contains(output))
        {
            System.out.print(">> ");
            output = console.next().charAt(0);
        }
        return output;
    }
}