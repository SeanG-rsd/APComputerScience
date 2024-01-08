import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

public class Board extends MouseInputAdapter
{
    static int[][] pieces;
    private static GamePanel panel;

    public static void main(String[] args)
    {
        pieces = new int[8][8];

        JFrame frame = new JFrame();
        frame.setBackground(Color.BLUE);
        frame.setTitle("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new GamePanel();
        MousePointGUI gui = new MousePointGUI(panel);

        panel.addMouseListener(gui);
        panel.addMouseMotionListener(gui);

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

    public void mouseMoved(MouseEvent event)
    {
        System.out.println(event.getPoint());
        JOptionPane.showMessageDialog(null, "Mouse pressed at position {" + event.getX() + ", " + event.getY() + "}");
    }
}
