import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel
{
    private static final int pieceWidth = 100;
    private static final int boardDimension = 8;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(pieceWidth * boardDimension, pieceWidth * boardDimension));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.fillRect(20,40,70,30);

    }

    public void addRect()
    {

    }
}
