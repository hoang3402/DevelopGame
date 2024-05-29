import javax.swing.*;
import java.awt.*;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class GameBoard extends JPanel implements Runnable {

    private final static int BOARD_WIDTH = 360;
    private final static int BOARD_HEIGHT = 600;
    public static int LEFT_X;
    public static int RIGHT_X;
    public static int TOP_Y;
    public static int BOTTOM_Y;

    private final Thread gameThread = new Thread(this);

    public GameBoard() {

        LEFT_X = (Main.WIDTH - BOARD_WIDTH) / 2;
        RIGHT_X = LEFT_X + BOARD_WIDTH;
        TOP_Y = 50;
        BOTTOM_Y = TOP_Y + BOARD_HEIGHT;

        setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        setLayout(null);
    }

    private void drawBoard(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawRect(LEFT_X, TOP_Y, BOARD_WIDTH + 8, BOARD_HEIGHT + 8);
    }

    private void drawNextBlock(Graphics2D graphics2D) {
        graphics2D.drawString("Next Block", RIGHT_X + 100, TOP_Y + 220);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawRect(RIGHT_X + 100, TOP_Y, 200, 200);
    }

    private void drawHoldBlock(Graphics2D graphics2D) {
        graphics2D.drawString("Hold Block", LEFT_X - 300, TOP_Y + 220);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawRect(LEFT_X - 300, TOP_Y, 200, 200);
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

        drawBoard(graphics2D);
        drawNextBlock(graphics2D);
        drawHoldBlock(graphics2D);
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
