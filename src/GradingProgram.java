/*
Requires:
variables, data types, and numerical operators
basic input/output
logic (if statements, switch statements)

Write a program that allows the user to enter the grade scored in a programming class (0-100).
If the user scored a 100 then notify the user that they got a perfect score.

★ Modify the program so that if the user scored a 90-100 it informs the user that they scored an A

★★ Modify the program so that it will notify the user of their letter grade
0-59 F 60-69 D 70-79 C 80-89 B 90-100 A
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;

public class GradingProgram {
    int score = 0;

    public void Input() throws Exception {
        do {
            System.out.print("Enter your score: ");
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String input_score = input.readLine();

            if (input_score.isBlank() || input_score.isEmpty()) {
                throw new Exception("You did not enter a score.");
            }

            score = parseInt(input_score);

            if (score < 0 || score > 100) {
                System.out.println("Score must be between 0 and 100.");
            }
        } while (score < 0 || score > 100);
    }

    public String GetGrade() {
        if (score >= 90) {
            if (score == 100) {
                System.out.println("Perfect Score!");
            }
            return "A";
        }
        if (score >= 80) {
            return "B";
        }
        if (score >= 70) {
            return "C";
        }
        if (score >= 60) {
            return "D";
        }
        return "F";
    }
}

