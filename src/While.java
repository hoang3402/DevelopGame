/*
Requires:
variables, data types, and numerical operators
basic input/output
logic (if statements, switch statements)
loops (for, while, do-while)

Write a program that continues to ask the user to enter any number other than 5 until the user enters the number 5.
Then tell the user "Hey! you weren't supposed to enter 5!" and exit the program.

★ Modify the program so that after 10 iterations if the user still hasn't entered 5 will tell the user
"Wow, you're more patient then I am, you win." and exit.

★★ Modify the program so that it asks the user to enter any number other than the number equal
to the number of times they've been asked to enter a number.
(i.e. on the first iteration "Please enter any number other than 0" and on the second iteration
"Please enter any number other than 1"m etc. etc.
The program must behave accordingly exiting when the user enters the number they were asked not to.)
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class While {

    private int number = 0;

    private String Core() throws Exception {
        int input_number;
        System.out.printf("Please enter any number other than %d: ", number);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        input_number = Integer.parseInt(input.readLine());

        if (input_number == number) {
            return String.format("Hey! you weren't supposed to enter %d!", number);
        }

        if (number + 1 == 10) {
            return "Wow, you're more patient then I am, you win.";
        }
        number++;
        return "";
    }

    public void StartDoWhile() throws Exception {
        String result;
        do {
            result = Core();
        } while (result.isEmpty());
        System.out.println(result);
    }

    public void StartWhile() throws Exception {
        String result = "";
        while (result.isEmpty()) {
            result = Core();
        }
        System.out.println(result);
    }

    public void StartFor() throws Exception {
//        for (int i = 0; i < 10; i++) {
//            Core();
//        }
        String result = "";
        for (; result.isEmpty(); ) {
            result = Core();
        }
        System.out.println(result);
    }
}
