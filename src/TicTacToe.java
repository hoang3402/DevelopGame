/*

Game board :

      |---|---|---|
      | 1 | 2 | 3 |
      |-----------|
      | 4 | 5 | 6 |
      |-----------|
      | 7 | 8 | 9 |
      |---|---|---|

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TicTacToe {

    int[][] board = new int[3][3];

    // -1 is not done
    // 0 is draw
    // 1 is X win
    // 2 is O win
//    private int isSolved() {
//        for (int i = 0; i < 8; i++) {
//            String line = switch (i) {
//                case 0 -> board[0][0] + " " + board[0][1] + " " + board[0][2];
//                case 1 -> board[1][0] + " " + board[1][1] + " " + board[1][2];
//                case 2 -> board[2][0] + " " + board[2][1] + " " + board[2][2];
//                case 3 -> board[0][0] + " " + board[1][0] + " " + board[2][0];
//                case 4 -> board[0][1] + " " + board[1][1] + " " + board[2][1];
//                case 5 -> board[0][2] + " " + board[1][2] + " " + board[2][2];
//                case 6 -> board[0][0] + " " + board[1][1] + " " + board[2][2];
//                case 7 -> board[0][2] + " " + board[1][1] + " " + board[2][0];
//                default -> "";
//            };
//
//            if (line.equals("1 1 1")) {
//                return 1;
//            }
//
//            if (line.equals("2 2 2")) {
//                return 2;
//            }
//        }
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (board[i][j] == 0) {
//                    return -1;
//                }
//            }
//        }
//        return 0;
//    }

    private int isSolvedBetter() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] > 0)
                return board[i][0];
            else if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] > 0)
                return board[0][i];
            else if ((board[1][1] == board[0][0] && board[0][0] == board[2][2] && board[1][1] > 0)
                    || board[1][1] == board[2][0] && board[2][0] == board[0][2] && board[1][1] > 0)
                return board[1][1];
        }
        if (Arrays.stream(board).flatMapToInt(Arrays::stream).anyMatch(n -> n == 0))
            return -1;
        else
            return 0;
    }

    private String playerToString(int player) {
        return switch (player) {
            case 1 -> "X";
            case 2 -> "O";
            default -> "";
        };
    }

    private void printBoard() {
        ClearConsole();
        System.out.println("|---|---|---|");
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                index++;
                if (j == 0) {
                    System.out.print("| " + (board[i][j] == 0 ? index : playerToString(board[i][j])) + " | ");
                } else if (j == 2) {
                    System.out.println((board[i][j] == 0 ? index : playerToString(board[i][j])) + " |");
                } else {
                    System.out.print((board[i][j] == 0 ? index : playerToString(board[i][j])) + " | ");
                }
            }
            if (i != 2) {
                System.out.println("|-----------|");
            }
        }
        System.out.println("|---|---|---|");
    }

    // 1 is X
    // 2 is O
    private void setBoard(int x, int y, boolean player) {
        board[x][y] = player ? 1 : 2;
    }

    public void Start() {
        boolean player = true;

        while (isSolvedBetter() == -1) {
            printBoard();
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                int x = Integer.parseInt(input.readLine());
                setBoard((x - 1) / 3, (x - 1) % 3, player);
                player = !player;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String result = switch (isSolvedBetter()) {
            case 0 -> "Draw";
            case 1 -> "X win";
            case 2 -> "O win";
            default -> "Error";
        };

        System.out.println(result);
    }

    public static void ClearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name"); // Check the current operating system

            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
