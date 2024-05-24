import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.lang.Thread.sleep;

public class Game extends JPanel {

    int score = 0;
    int tick = 500;
    boolean lock = false;

    Image title, fruit;
    Snake snake = new Snake(0, 0, this);
    Tile fruitTile;

    public Game() throws IOException {
        var _title = ImageIO.read(new File("src/assets/title.png"));
        var _fruit = ImageIO.read(new File("src/assets/fruit.png"));

        title = _title.getScaledInstance(Main.WIDTH, 80, Image.SCALE_SMOOTH);
        fruit = _fruit.getScaledInstance(Main.BLOCK_SIZE, Main.BLOCK_SIZE, Image.SCALE_SMOOTH);

        setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        setBackground(Color.BLACK);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                snake.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setFocusable(true);
        System.out.println("Loaded");
    }

    public void start() throws InterruptedException {
        placeFruit();

        while (true) {
            move();
            repaint();
            lock = false;
            sleep(tick);
        }
    }

    public void gameOver() {
        System.out.println("Game over");
        System.exit(0);
    }

    private void move() {
        snake.move();
    }

    void placeFruit() {
        Random random = new Random();
        fruitTile = new Tile(
                random.nextInt(Main.WIDTH / Main.BLOCK_SIZE),
                random.nextInt(Main.HEIGHT / Main.BLOCK_SIZE)
        );
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        // grid
        graphics2D.setColor(Color.WHITE);
        for (int i = 0; i < Main.WIDTH / Main.BLOCK_SIZE; i++) {
            graphics2D.drawLine(i * Main.BLOCK_SIZE, 0, i * Main.BLOCK_SIZE, Main.HEIGHT);
            graphics2D.drawLine(0, i * Main.BLOCK_SIZE, Main.WIDTH, i * Main.BLOCK_SIZE);
        }

        graphics2D.drawImage(title, 0, 0, this);

        snake.paint(graphics2D);

        if (fruitTile != null) {
            graphics2D.drawImage(fruit, fruitTile.x * Main.BLOCK_SIZE, fruitTile.y * Main.BLOCK_SIZE, this);
        }

        graphics2D.setColor(Color.WHITE);
        graphics2D.drawString("Score: " + score, 10, 85);
    }

    public boolean collides(Tile a, Tile b) {
        return a.x == b.x && a.y == b.y;
    }
}
