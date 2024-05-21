import javax.swing.*;
import java.awt.*;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.lang.Thread.sleep;

public class Main extends JPanel {

    Ball ball = new Ball(250, 350, 1, 1);

    private static void initScreen() {
        JFrame frame = new JFrame();
        Main game = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.add(game);
        frame.setVisible(true);
    }

    private void move() {
        ball.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        ball.paint(graphics2D);
    }

    private void gameLoop() throws InterruptedException {

        while (true) {
            move();
            sleep(30);
        }
    }

    public static void main(String[] args) {
        initScreen();
        Main g = new Main();
        try {
            g.gameLoop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}