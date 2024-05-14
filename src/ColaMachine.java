/*
Requires:
variables, data types, and numerical operators
basic input/output
logic (if statements, switch statements)

Write a program that presents the user w/ a choice of your 5 favorite beverages (Coke, Water, Sprite, ... , Whatever).
Then allow the user to choose a beverage by entering a number 1-5.
Output which beverage they chose.

★ If you program uses if statements instead of a switch statement, modify it to use a switch statement.
If instead your program uses a switch statement, modify it to use if/else-if statements.

★★ Modify the program so that if the user enters a choice other than 1-5 then it will output "Error.
choice was not valid, here is your money back."
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ColaMachine {

    private final String[] drinks = {"Coke", "Water", "Sprite", "Mountain Dew", "Tea"};

    private void PrintMenu() {
        System.out.println("Welcome to the Cola Machine!");
        for (int i = 0; i < drinks.length; i++) {
            System.out.println(i + 1 + ". " + drinks[i]);
        }
    }

    public void Start() throws Exception {
        PrintMenu();
        System.out.println("Please enter a number 1-5");
        System.out.print("Your choice: ");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int choice = Integer.parseInt(input.readLine());
        if (choice < 1 || choice > 5) {
            throw new Exception("Error. choice was not valid, here is your money back.");
        }

        System.out.println("You chose " + drinks[choice - 1]);
        IfStatements(choice);
        SwitchStatements(choice);
    }

    private void IfStatements(int choice) {
        if (choice == 1) {
            System.out.println("You chose Coke");
        } else if (choice == 2) {
            System.out.println("You chose Water");
        } else if (choice == 3) {
            System.out.println("You chose Sprite");
        } else if (choice == 4) {
            System.out.println("You chose Mountain Dew");
        } else if (choice == 5) {
            System.out.println("You chose Tea");
        }
    }

    private void SwitchStatements(int choice) {
        switch (choice) {
            case 1:
                System.out.println("You chose Coke");
                break;
            case 2:
                System.out.println("You chose Water");
                break;
            case 3:
                System.out.println("You chose Sprite");
                break;
            case 4:
                System.out.println("You chose Mountain Dew");
                break;
            case 5:
                System.out.println("You chose Tea");
                break;
        }
    }
}
