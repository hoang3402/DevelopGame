package Core;

import javax.swing.*;
import java.io.IOException;

import static Core.Setting.title;

public class Main {

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