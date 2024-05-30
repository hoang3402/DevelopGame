import javax.swing.*;

public class Main {

    final static int WIDTH = 1280;
    final static int HEIGHT = 720;
    final static int TILE_SIZE = 30;
    final static int FPS = 60;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");

        GameBoard gameBoard = new GameBoard();
        frame.add(gameBoard);
        frame.pack();

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        gameBoard.start();
    }
}