//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        try {
            ticTacToe.Start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}