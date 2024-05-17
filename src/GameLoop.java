import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.lang.Thread.sleep;


public class GameLoop extends JPanel {
    Ball ball = new Ball(this);
    Racquet racquet = new Racquet(this, 0, 0, 330);
    Racquet racquetEnemy = new Racquet(this, 100, 0, 30);
    int speed = 1;

    private int getScore() {
        return speed - 1;
    }

    public GameLoop() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D) {
                    racquetEnemy.keyReleased();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    racquet.keyReleased();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    racquetEnemy.keyPressed(RacquetDirection.RIGHT);
                }
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    racquetEnemy.keyPressed(RacquetDirection.LEFT);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    racquet.keyPressed(RacquetDirection.RIGHT);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    racquet.keyPressed(RacquetDirection.LEFT);
                }
            }
        });
        setFocusable(true);
    }

    private void move() {
        ball.move();
        racquetEnemy.move();
        racquet.move();
    }

    public void gameOver() {
        MyAudio.stopAll();
        MyAudio.play("GAMEOVER");
        String player = getScore() % 2 != 0 ? "Player 1" : "Player 2";
        JOptionPane.showMessageDialog(this, player + " wins!\nyour score is: " + getScore(),
                "Game Over", JOptionPane.ERROR_MESSAGE);
        System.exit(ABORT);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        ball.paint(graphics2D);
        racquet.paint(graphics2D);
        racquetEnemy.paint(graphics2D);

        graphics2D.setColor(Color.GRAY);
        graphics2D.setFont(new Font("Verdana", Font.BOLD, 30));
        graphics2D.drawString(String.valueOf(getScore()), 10, 30);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        GameLoop game = new GameLoop();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.add(game);
        frame.setVisible(true);
        MyAudio.playLoop("BACK");

        while (true) {
            game.move();
            game.repaint();
            sleep(30);
        }
    }
}
