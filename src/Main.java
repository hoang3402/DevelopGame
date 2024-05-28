import javax.swing.*;

public class Main {

    final static int WIDTH = 800;
    final static int HEIGHT = 800;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");

        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();

    }
}