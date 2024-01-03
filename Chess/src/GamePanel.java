import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
public class GamePanel extends JPanel
{
    private static final int pieceWidth = 100;
    private static final int boardDimension = 8;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(pieceWidth * boardDimension, pieceWidth * boardDimension));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        drawBoard(g);
        drawPieces(g);

    }

    public void Repaint()
    {
        repaint();
    }

    public static void drawBoard(Graphics g)
    {
        g.setColor(Color.PINK);

        for (int x = 0; x < boardDimension; ++x)
        {
            for (int y = 0; y < boardDimension; ++y)
            {
                if ((x % 2 == 1 || y % 2 == 1) && !(x % 2 == 1 && y % 2 == 1))
                {
                    g.fillRect(pieceWidth * x, pieceWidth * y, pieceWidth, pieceWidth);
                }
            }
        }
    }

    public static void drawPieces(Graphics g)
    {
        BufferedImage piece;
        try {
            piece = ImageIO.read(new File("/Users/gutmannse/Desktop/gutmannsean/APComputerScience/Chess/images/BlackRook.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f);
        Graphics2D g2d = (Graphics2D) piece.getGraphics();
        g2d.setBackground(new Color(0, true));
        g2d.clearRect(0, 0, pieceWidth, pieceWidth);
        g2d.dispose();

        g.drawImage(piece, 0, 0, pieceWidth, pieceWidth, null);
    }
}
