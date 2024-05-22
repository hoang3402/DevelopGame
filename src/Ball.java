import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Ball extends JPanel {
    int x;
    int y;
    int xSpeed;
    int ySpeed;

    final int DIAMETER = 30;

    private Image image;
    private final Main game;

    public Ball(int x, int y, int xSpeed, int ySpeed, Main game) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.game = game;
        try {
            var _temp = ImageIO.read(new File("src/assets/58-Breakout-Tiles.png"));
            image = _temp.getScaledInstance(DIAMETER, DIAMETER, Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void move() {

        // check if ball is out of screen
        if (x < 0 || x > game.getWidth() - DIAMETER) {
            xSpeed *= -1;
        }
        if (y < 0 || y > game.getHeight() - DIAMETER) {
            ySpeed *= -1;
        }

        if (collisionWithRacquet()) {
            ySpeed *= -1;
            game.level += 1;
        }

        for (int i = 0; i < game.bricks.length; i++) {
            Brick brick = game.bricks[i];
            if (brick == null) {
                continue;
            }
            if (collisionWithBrick(brick)) {
                ySpeed *= -1;
                game.removeBrick(i);
            }
        }

        x += xSpeed;
        y += ySpeed;
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(image, x, y, this);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }

    private boolean collisionWithRacquet() {
        return game.racquet.getBounds().intersects(getBounds());
    }

    private boolean collisionWithBrick(Brick brick) {
        return brick.getBounds().intersects(getBounds());
    }
}
