import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class Main extends JPanel {

    final int BrickPerRow = 7;

    Ball ball = new Ball(250, 250, 1, -1, this);
    Racquet racquet = new Racquet(250, 300, 16, 86, 0, this);
    Brick[] bricks = new Brick[21];

    int level = 1;
    int score = 0;

    public Main() {
        // blind key
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                    racquet.keyPressed(RacquetDirection.LEFT);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                    racquet.keyPressed(RacquetDirection.RIGHT);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT ||
                        e.getKeyCode() == KeyEvent.VK_A ||
                        e.getKeyCode() == KeyEvent.VK_RIGHT ||
                        e.getKeyCode() == KeyEvent.VK_D) {
                    racquet.keyReleased();
                }
            }
        });

        // init bricks
        for (int i = 0; i < bricks.length; i++) {
            int x = i % BrickPerRow * 64 + 22;
            int y = i / BrickPerRow * 21 + 22;
            bricks[i] = new Brick(x, y);
        }

        setFocusable(true);
    }

    private static void initScreen(Main game) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.add(game);
        frame.setVisible(true);
    }

    private void move() {
        ball.move();
        racquet.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        ball.paint(graphics2D);
        racquet.paint(graphics2D);

        for (Brick brick : bricks) {
            if (brick == null) {
                continue;
            }
            brick.paint(graphics2D);
        }

        g.drawString("Score: " + score, 10, 20);
        g.drawString("Level: " + level, 400, 20);
    }

    public void removeBrick(int i) {
        bricks[i] = null;
    }

    public void gameOver() {
        System.out.println("Game Over");
        JOptionPane.showMessageDialog(this,
                "Score: " + score,
                "Game Over",
                JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public void gameWin() {
        System.out.println("Game Win");
        JOptionPane.showMessageDialog(this,
                "Score: " + score,
                "Game Win",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private void gameLoop() throws InterruptedException {
        while (true) {
            move();
            repaint();
            Thread.sleep(30);
        }
    }

    public static void main(String[] args) {
        Main g = new Main();
        initScreen(g);
        try {
            g.gameLoop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}