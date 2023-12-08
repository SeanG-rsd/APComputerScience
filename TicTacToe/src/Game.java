import java.util.Scanner;
public class Game
{
    static char[] icons = new char[] {'x', 'o'};
    static int whoseTurn = 0;

    static int size;

    static Scanner console;

    public static void main(String[] args)
    {
        size = 3;
        Board b = new Board(size);
        console = new Scanner(System.in);

        while (!b.checkForWin(icons))
        {
            b.print();
            b.setChar(GetPlay(b), icons[whoseTurn]);
            if (whoseTurn == 1) {whoseTurn = 0;}
            else {whoseTurn++;}
        }
        System.out.println("win");
    }

    public static int[] GetPlay(Board b)
    {
        System.out.println("It is player " + (whoseTurn + 1) + "'s turn.");
        System.out.print("Enter the x coordinate : ");
        int x = console.nextInt();
        System.out.print("Enter the y coordinate : ");
        int y = console.nextInt();

        if (x > size || x < 1 || y > size || y < 1 || b.isTaken(new int[] {y - 1, x- 1}))
        {
            System.out.println("\nInvalid entry.\n");
            return GetPlay(b);
        }
        return new int[] {y - 1, x - 1};
    }
}
