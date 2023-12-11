import java.util.*;

public class MinesweeperGame
{
    public static void main(String[] args)
    {
        BoardGenerator boardGenerator = new BoardGenerator(10, 10,10);
        boardGenerator.print();

        boardGenerator.GuessSpot(5, 5, new ArrayList<>(List.of(5 * 8 + 5)));
        boardGenerator.print();
    }
}