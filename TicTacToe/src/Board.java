public class Board
{
    char playerOne = 'x';
    char playerTwo = 'o';

    int size;
    char[][] info;

    public Board(int s)
    {
        size = s;
        info = new char[s][s];
    }

    public boolean squareIsTaken(int r, int c)
    {
        return info[r][c] != '\u0000';
    }

    public void print()
    {
        System.out.println();
        for (int r = 0; r < size - 1; ++r)
        {
            printRow(r);
            System.out.println("-----------");
        }
        printRow(size - 1);
    }

    public void printRow(int r)
    {
        System.out.println("   |   |   ");
        for (int c = 0; c < size - 1; ++c)
        {
            System.out.print(" " + getChar(new int[] {r, c}) + " |");
        }
        System.out.println(" " + getChar(new int[] {r, size - 1}));
        System.out.println("   |   |   ");
    }

    public void setChar(int[] spot, char c)
    {
        info[spot[0]][spot[1]] = c;
    }

    public boolean isTaken(int[] spot)
    {
        return info[spot[0]][spot[1]] != '\u0000';
    }

    public char getChar(int[] spot)
    {
        if (info[spot[0]][spot[1]] == '\u0000') {return ' ';}
        return info[spot[0]][spot[1]];
    }

    public boolean checkForWin(char[] players)
    {
        for (char check : players) {
            boolean horizontal = true;
            boolean vertical = true;

            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j)
                {
                    if (info[i][j] != check) {
                        horizontal = false;
                    }
                    if (info[j][i] != check) {
                        vertical = false;
                    }
                }

                if (horizontal) {return true;}
                horizontal = true;
                if (vertical) {return true;}
                vertical = true;
            }

            boolean leftDiagonal = true;
            boolean rightDiagonal = true;
            for (int i = 0; i < size; ++i) {
                if (info[i][i] != check) {
                    leftDiagonal = false;
                }
                if (info[i][size - 1] != check) {
                    rightDiagonal = false;
                }
            }

            if (rightDiagonal || leftDiagonal)
            {
                return true;}
        }

        return false;
    }
}
