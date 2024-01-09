import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MousePointGUI extends MouseInputAdapter
{
    private GamePanel panel;

    public MousePointGUI(GamePanel panel)
    {
        this.panel = panel;
    }

    public void mouseMoved(MouseEvent event)
    {

    }

    public void mouseClicked(MouseEvent event)
    {
        panel.ClickPiece(event.getX(), event.getY());
    }
}
