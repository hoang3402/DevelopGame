import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.lang.Thread.sleep;


public class GameLoop extends JPanel {
    Ball ball = new Ball(this);
    Racquet racquet = new Racquet(this);

    public GameLoop() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                racquet.keyReleased();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                racquet.keyPressed(e);
            }
        });
        setFocusable(true);
    }

    private void move() {
        ball.move();
        racquet.move();
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.ERROR_MESSAGE);
        System.exit(ABORT);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        ball.paint(graphics2D);
        racquet.paint(graphics2D);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        GameLoop game = new GameLoop();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(game);
        frame.setVisible(true);
        playSound(MyAudio.BACK);

        while (true) {
            game.move();
            game.repaint();
            sleep(30);
        }
    }

    public static synchronized void playSound(final String sound) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        Objects.requireNonNull(Main.class.getResourceAsStream(sound)));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }
}
