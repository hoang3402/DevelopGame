import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Ball extends JPanel {
    int x;
    int y;
    int xSpeed;
    int ySpeed;
    final int RADIUS = 30;
    private Image image;
    final private Main game;

    public Ball(int x, int y, int xSpeed, int ySpeed, Main game) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.game = game;
        try {
            var _temp = ImageIO.read(new File("src/assets/58-Breakout-Tiles.png"));
            image = _temp.getScaledInstance(RADIUS, RADIUS, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void move() {

        // check if ball is out of screen
        if (x < 0 || x > game.getWidth() - RADIUS) {
            xSpeed *= -1;
        }
        if (y < 0 || y > game.getHeight() - RADIUS) {
            ySpeed *= -1;
        }

        x += xSpeed;
        y += ySpeed;
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(image, x, y, this);
    }
}
