import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import javax.swing.*;
public class Board
{
    static int[][] pieces;

    enum White {p, n, b, r, q , k};
    enum Black {P, N, B, R, Q, K};

    public static void main(String[] args)
    {
        pieces = new int[8][8];

        JFrame frame = new JFrame();
        frame.setBackground(Color.BLUE);
        frame.setTitle("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }
    public Board()
    {
        pieces = new int[8][8];
    }

    public static void initialize()
    {

    }
}
