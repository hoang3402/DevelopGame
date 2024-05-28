import javax.swing.*;
import java.awt.*;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class GameBoard extends JPanel {

    private int WIDTH, HEIGHT;

    public GameBoard(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));
        setFocusable(true);
    }

    public void start() throws InterruptedException {
        while (true) {
            move();
            repaint();
            Thread.sleep(30);
        }
    }

    private void move() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        // draw grid
        g.setColor(Color.black);
        for (int i = 0; i < WIDTH; i += Main.TILE_SIZE) {
            g.drawLine(i, 0, i, HEIGHT);
        }
        for (int i = 0; i < HEIGHT; i += Main.TILE_SIZE) {
            g.drawLine(0, i, WIDTH, i);
        }
    }
}
