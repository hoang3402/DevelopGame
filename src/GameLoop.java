import javax.swing.*;
import java.awt.*;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.lang.Thread.sleep;

public class GameLoop extends JPanel {
    Ball ball = new Ball(this);

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

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        GameLoop game = new GameLoop();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(game);
        frame.setVisible(true);

        while (true) {
            game.move();
            game.repaint();
            sleep(30);
        }
    }
}
