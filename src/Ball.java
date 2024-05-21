import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ball extends JPanel {
    int x;
    int y;
    int xSpeed;
    int ySpeed;
    final int RADIUS = 30;
    private BufferedImage image;

    public Ball(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        try {
            image = ImageIO.read(new File("src/assets/58-Breakout-Tiles.png"));
        } catch (IOException ex) {
            System.out.println("load failed: " + ex.getMessage());
        }
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;
    }

    public void paint(Graphics g) {
        super.paint(g);

        // scale size image
        var _image = image.getScaledInstance(RADIUS, RADIUS, Image.SCALE_SMOOTH);
        g.drawImage(_image, x, y, this);
    }
}
