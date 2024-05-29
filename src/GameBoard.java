import javax.swing.*;
import java.awt.*;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class GameBoard extends JPanel implements Runnable {

    private final Thread gameThread = new Thread(this);

    public GameBoard() {
        setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        setLayout(null);
    }

    public void start() {
        gameThread.start();
    }

    private void update() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);


    }

    @Override
    public void run() {

        // GAME LOOP
        var drawInterval = 1000000000 / Main.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread.isAlive()) {
            currentTime = System.nanoTime();
            delta += (double) (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
}
