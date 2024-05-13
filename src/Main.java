import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        GradingProgram program = new GradingProgram();
        try {
            program.Input();
            String result = program.GetGrade();
            System.out.println("Your grade is: " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}