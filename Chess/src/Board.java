public class Board
{
    static int[][] pieces;

    enum White {p, n, b, r, q , k};
    enum Black {P, N, B, R, Q, K};

    public static void main(String[] args)
    {
        pieces = new int[8][8];
        print();
    }
    public Board()
    {
        pieces = new int[8][8];
    }

    public static void print()
    {
        System.out.println("---------------------------------");
        for (int i = 0; i < pieces.length; ++i)
        {
            System.out.print("|");
            for (int j = 0; j < pieces[i].length; ++j)
            {
                System.out.print(" " + pieces[i][j] + " |");
            }
            System.out.println("\n---------------------------------");
        }
    }
}
