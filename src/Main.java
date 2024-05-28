import javax.swing.*;

public class Main {

    final static int WIDTH = 800;
    final static int HEIGHT = 800;
    final static int TILE_SIZE = 30;

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Tetris");
        GameBoard gameBoard = new GameBoard(500, 800);

        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gameBoard);
        frame.pack();

        gameBoard.start();
    }
}