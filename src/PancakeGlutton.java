/*
Requires:
variables, data types, and numerical operators
basic input/output
logic (if statements, switch statements)
loops (for, while, do-while)
arrays

Write a program that asks the user to enter the number of pancakes eaten for breakfast by 10 different people
(Person 1, Person 2, ..., Person 10)
Once the data has been entered the program must analyze the data and output which person ate the most pancakes for breakfast.

★ Modify the program so that it also outputs which person ate the least number of pancakes for breakfast.

★★★★ Modify the program so that it outputs a list in order of number of pancakes eaten of all 10 people.
i.e.
Person 4: ate 10 pancakes
Person 3: ate 7 pancakes
Person 8: ate 4 pancakes
...
Person 5: ate 0 pancakes
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PancakeGlutton {

    private final int NumberOfPeople = 10;
    private final int[][] pancakesEaten = new int[NumberOfPeople][2];

    private int Input() throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        return Integer.parseInt(input.readLine());
    }

    private void Sort() {
        for (int i = 0; i < pancakesEaten.length; i++) {
            for (int j = i + 1; j < pancakesEaten.length; j++) {
                if (pancakesEaten[i][1] < pancakesEaten[j][1]) {
                    int[] temp = pancakesEaten[i];
                    pancakesEaten[i] = pancakesEaten[j];
                    pancakesEaten[j] = temp;
                }
            }
        }
    }

    private void Print() {
        for (int[] its : pancakesEaten) {
            System.out.println("Person " + its[0] + ": ate " + its[1] + " pancakes");
        }
    }

    public void Start() {
        for (int i = 0; i < NumberOfPeople; i++) {
            try {
                System.out.print("Person " + (i + 1) + ": ");
                pancakesEaten[i][0] = i + 1;
                pancakesEaten[i][1] = Input();
                if (pancakesEaten[i][1] < 0) {
                    throw new Exception("Invalid input");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                i--;
            }
        }

        Sort();
        Print();
    }
}
