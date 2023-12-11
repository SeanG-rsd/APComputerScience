import java.util.*;

public class BoardGenerator
{
    static int SIZE_X;
    static int SIZE_Y;
    static int MINE_COUNT;

    static boolean[][] mineInfo;
    static int[][] spaceInfo;
    static int[][] visual;

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
                    } else if (info == -1) {
                        System.out.print("F ");
                    } else {
                        System.out.print(info + " ");
                    }
                }
                else
                {
                    System.out.print("@ ");
                }
            }
            System.out.println();
        }
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

    public void GuessSpot(int row, int column, List<Integer> checked)
    {
        if (spaceInfo[row][column] == -1)
        {
            visual[row][column] = 1;
        }
        else
        {
            for (int r = row - 1; r <= row + 1; ++r)
            {
                for (int c = column - 1; c <= column + 1; ++c)
                {
                    if (IsWithinBorder(r, c) && !checked.contains(r * 8 + c))
                    {
                        visual[row][column] = 1;
                        checked.add(r * 8 + c);
                        GuessSpot(r, c, checked);
                    }
                }
            }
        }
    }

    public static void FlagSpot()
    {

    }
}
