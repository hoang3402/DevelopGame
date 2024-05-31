package Core;

import javax.swing.*;

public class Main {

    final static int WIDTH = 1280;
    final static int HEIGHT = 720;
    public final static int TILE_SIZE = 30;
    final static int FPS = 60;
    final static String title = "Tetris";


    public static void main(String[] args) {
        JFrame frame = new JFrame(title);

        GameManager gameBoard = new GameManager();
        frame.add(gameBoard);
        frame.pack();

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        gameBoard.start();
    }
}