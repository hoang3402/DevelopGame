import javax.swing.*;
import java.io.IOException;

public class Main extends JPanel {
    final static int WIDTH = 800;
    final static int HEIGHT = 800;
    final static int BLOCK_SIZE = 25;

    public static void main(String[] args) throws IOException, InterruptedException {
        JFrame frame = new JFrame("Snake Game");
        Game game = new Game();

        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(game);
        frame.pack();

        game.start();
    }
}