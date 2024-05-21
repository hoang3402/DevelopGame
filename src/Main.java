import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class Main extends JPanel {

    Ball ball = new Ball(250, 250, 1, -1, this);
    Racquet racquet = new Racquet(250, 310, 30, 60, 0, this);
    int level = 1;

    public Main() {
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
                racquet.keyReleased();
            }
        });
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