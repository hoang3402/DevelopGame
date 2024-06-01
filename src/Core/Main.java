package Core;

import javax.swing.*;
import java.io.IOException;

public class Main {

    final static int WIDTH = 1280;
    final static int HEIGHT = 720;
    public final static int TILE_SIZE = 30;
    final static int FPS = 60;
    final static String title = "Tetris";


    public static void main(String[] args) {
        JFrame frame = new JFrame(title);

        GameManager gameBoard;
        try {
            gameBoard = new GameManager();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        frame.add(gameBoard);
        frame.pack();

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        gameBoard.start();
    }
}