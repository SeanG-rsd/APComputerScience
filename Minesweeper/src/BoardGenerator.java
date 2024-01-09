import java.util.*;

public class BoardGenerator
{
    static int SIZE_X;
    static int SIZE_Y;
    static int MINE_COUNT;

    static boolean[][] mineInfo;
    static int[][] spaceInfo;
    static int[][] visual;

    public boolean hasLost;
    public int flagCount;

    public BoardGenerator(int x, int y, int m)
    {
        SIZE_X = x;
        SIZE_Y = y;
        MINE_COUNT = m;
        mineInfo = new boolean[x][y];
        spaceInfo = new int[x][y];
        visual = new int[x][y];

        PlaceMines();
        PlaceNumbers();
    }

    public void print()
    {
        for (int row = 0; row < spaceInfo.length; ++row)
        {
            for (int column = 0; column < spaceInfo[row].length; ++column)
            {
                int info = spaceInfo[row][column];
                int isVisible = visual[row][column];
                if (isVisible == 1)
                {
                    if (info == 0) {
                        System.out.print("- ");
                    } else if (info == -1) { // mine
                        System.out.print("F ");
                    } else {
                        System.out.print(info + " ");
                    }
                }
                else if (isVisible == 2)
                {
                    System.out.print("F ");
                }
                else
                {
                    System.out.print("@ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void PlaceMines()
    {
        int placed = 0;
        Random r = new Random();
        while (placed < MINE_COUNT)
        {
            int spot = r.nextInt(SIZE_X * SIZE_Y);
            if (!mineInfo[spot / SIZE_X][spot % SIZE_Y])
            {
                mineInfo[spot / SIZE_X][spot % SIZE_Y] = true;
                spaceInfo[spot / SIZE_X][spot % SIZE_Y] = -1;
                placed++;
            }
        }
    }

    private static void PlaceNumbers()
    {
        for (int r = 0; r < spaceInfo.length; ++r) {
            for (int c = 0; c < spaceInfo[r].length; ++c) {
                for (int row = r - 1; row <= r + 1; ++row)
                {
                    for (int column = c - 1; column <= c + 1; ++column)
                    {
                        if (IsWithinBorder(row, column))
                        {
                            if (mineInfo[row][column] && !mineInfo[r][c])
                            {
                                spaceInfo[r][c]++;
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean IsWithinBorder(int r, int c)
    {
        return r >= 0 && r < SIZE_X && c >= 0 && c < SIZE_Y;
    }

    public void GuessSpot(int row, int column)
    {
        if (spaceInfo[row][column] == -1)
        {
            hasLost = true;
            return;
        }

        if (visual[row][column] != 0)
        {
            return;
        }

        //visual[row][column] = 1;
        GuessSpot(row, column, new LinkedList<>(List.of((row) * SIZE_X + column)));

    }

    public void GuessSpot(int row, int column, List<Integer> checked)
    {
        if (spaceInfo[row][column] != 0)
        {
            visual[row][column] = 1;
        }
        else
        {
            visual[row][column] = 1;
            for (int r = row - 1; r <= row + 1; ++r)
            {
                for (int c = column - 1; c <= column + 1; ++c)
                {
                    if (IsWithinBorder(r, c) && !checked.contains(r * SIZE_X + c))
                    {
                        if (spaceInfo[r][c] == 0) {

                            if (r == 9 && c == 9 && !checked.contains(r * SIZE_X + c))
                            {
                                System.out.println(visual[r][c]);
                            }

                            checked.add(r * SIZE_X + c);
                            GuessSpot(r, c, checked);
                        } else if (spaceInfo[r][c] != -1) {
                            visual[r][c] = 1;
                        }
                    }
                }
            }
        }
    }

    public void flagSpot(int row, int column)
    {
        if (visual[row][column] == 2)
        {
            visual[row][column] = 0;
            flagCount--;
        }
        else if (visual[row][column] == 0)
        {
            visual[row][column] = 2;
            flagCount++;
        }
    }

    public static boolean checkForMine(int row, int column)
    {
        return spaceInfo[row][column] == -1;
    }
}
