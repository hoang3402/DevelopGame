import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Brick extends JPanel {
    int x,y;
    final int width = 64;
    final int height = 21;
    private Image image;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            var _temp = ImageIO.read(new File("src/assets/01-Breakout-Tiles.png"));
            image = _temp.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(image, x, y, this);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
