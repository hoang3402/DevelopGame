package Core;

import Block.Block;
import Helper.Direction;
import Helper.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class GameManager extends JPanel implements Runnable, KeyListener {

    private final static int BOARD_WIDTH = 360;
    private final static int BOARD_HEIGHT = 600;

    public static int LEFT_X;
    public static int RIGHT_X;
    public static int TOP_Y;
    public static int BOTTOM_Y;

    private final GameState gameState;

    private Thread gameThread;

    public GameManager() throws IOException {

        LEFT_X = (Main.WIDTH - BOARD_WIDTH) / 2;
        RIGHT_X = LEFT_X + BOARD_WIDTH;
        TOP_Y = 60;
        BOTTOM_Y = TOP_Y + BOARD_HEIGHT;

        gameState = new GameState();

        addKeyListener(this);
        setFocusable(true);

        setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        setLayout(null);
    }

    private void drawBoard(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawRect(LEFT_X, TOP_Y, BOARD_WIDTH, BOARD_HEIGHT);

        var gameGrid = gameState.gameGrid.gameGrid;
        for (int i = 0; i < gameGrid.length; i++) {
            var tile = gameGrid[i];
            for (int j = 0; j < tile.length; j++) {
                if (tile[j] == 0) continue;
                graphics2D.setColor(Color.BLACK);
                Block block = gameState.blockQueue.blocks[tile[j] - 1];
                graphics2D.drawImage(block.image,
                        i * Main.TILE_SIZE + LEFT_X,
                        j * Main.TILE_SIZE,
                        null);
            }
        }
    }

    private void drawFrameNextBlock(Graphics2D graphics2D) {
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 30));
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString("Next", RIGHT_X + 165, TOP_Y + 60);
        graphics2D.drawRect(RIGHT_X + 100, TOP_Y, 200, 200);

        graphics2D.setColor(Color.BLACK);
        Position[] _nextBlock = gameState.nextBlock.tiles[0];
        for (var position : _nextBlock) {
            if (gameState.nextBlock.image != null) {
                graphics2D.drawImage(
                        gameState.nextBlock.image,
                        position.x * Main.TILE_SIZE + RIGHT_X + 160,
                        position.y * Main.TILE_SIZE + TOP_Y + 75,
                        null
                );
            } else {
                System.out.println("Something is wrong");
                graphics2D.fillRect(
                        position.x * Main.TILE_SIZE + RIGHT_X + 160,
                        position.y * Main.TILE_SIZE + TOP_Y + 75,
                        Main.TILE_SIZE, Main.TILE_SIZE
                );
            }
        }
    }

    private void drawFrameHoldBlock(Graphics2D graphics2D) {
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 30));
        graphics2D.drawString("Hold", LEFT_X - 230, TOP_Y + 60);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawRect(LEFT_X - 300, TOP_Y, 200, 200);

        Block holdBlock = gameState.holdBlock;
        if (holdBlock == null) return;

        graphics2D.setColor(Color.BLACK);
        Position[] _holdBlock = holdBlock.tiles[0];
        for (var position : _holdBlock) {
            if (holdBlock.image != null) {
                graphics2D.drawImage(
                        holdBlock.image,
                        position.x * Main.TILE_SIZE + LEFT_X - 280,
                        position.y * Main.TILE_SIZE + TOP_Y + 75,
                        null
                );
            } else {
                System.out.println("Something is wrong");
                graphics2D.fillRect(
                        position.x * Main.TILE_SIZE + LEFT_X - 225,
                        position.y * Main.TILE_SIZE + TOP_Y + 75,
                        Main.TILE_SIZE, Main.TILE_SIZE
                );
            }
        }
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        // System.out.println("[" + gameState.blockCounter + "] update");

        if (gameState.gameOver) {
            System.out.println("Game Over");
            System.exit(0);
            return;
        }

        gameState.blockCounter += 1;
        if (gameState.blockCounter != gameState.dropInterval) return;

        gameState.move(Direction.DOWN);

        gameState.blockCounter = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);

        drawScore(graphics2D);
        drawBoard(graphics2D);
        drawFrameNextBlock(graphics2D);
        drawFrameHoldBlock(graphics2D);

        gameState.paint(graphics2D);
    }

    private void drawScore(Graphics2D graphics2D) {
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 30));
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString("Score: " + gameState.score, LEFT_X, TOP_Y - 10);
    }

    @Override
    public void run() {

        double drawInterval = (double) 1_000_000_000 / Main.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("key pressed: " + e.getKeyCode());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> gameState.move(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> gameState.move(Direction.RIGHT);
            case KeyEvent.VK_UP -> gameState.rotateCW();
            case KeyEvent.VK_DOWN -> gameState.moveDownInstantly();
            case KeyEvent.VK_SPACE -> gameState.setHoldBlock();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
