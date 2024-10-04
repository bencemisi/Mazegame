import javax.swing.*;
import java.awt.*;

/**
 * A szoba panelje, amely a háttérképet jeleníti meg.
 */
public class RoomPanel extends JPanel {
    /**
     * A háttérkép
     */
    private ImageIcon background;

    /**
     * A háttérkép beállítása.
     * @param path a kép elérési útja
     */
    public void setBackground(String path) {
        background = new ImageIcon(path);
        repaint();
    }

    /**
     * A kép kirajzolása
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }

}
