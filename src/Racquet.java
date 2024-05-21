import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

enum RacquetDirection {
    LEFT, RIGHT
}

public class Racquet extends JPanel {
    int locationX;
    int locationY;
    int height;
    int width;
    int speed;
    private Image image;
    final private Main game;

    public Racquet(int locationX, int locationY, int height, int width, int speed, Main game) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.game = game;

        try {
            var _temp = ImageIO.read(new File("src/assets/56-Breakout-Tiles.png"));
            image = _temp.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void move() {
        if (locationX + speed > 0 && locationX + height < game.getWidth() - width) {
            locationX += speed;
        }
    }

    public void keyReleased() {
        speed = 0;
    }

    public void keyPressed(RacquetDirection direction) {
        if (direction == RacquetDirection.LEFT)
            speed = -game.level;
        if (direction == RacquetDirection.RIGHT)
            speed = game.level;
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(image, locationX, locationY, this);
    }

    public Rectangle getBounds() {
        return new Rectangle(locationX, locationY, WIDTH, HEIGHT);
    }

    public int getTopY() {
        return locationY;
    }
}
