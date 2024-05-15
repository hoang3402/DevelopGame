import javax.swing.*;

public class Adding {

    private static boolean isNumber(String n) {
        try {
            Integer.parseInt(n);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static void Main() {
        String n1 = JOptionPane.showInputDialog(null, "First Number");

        while (!isNumber(n1)) {
            n1 = JOptionPane.showInputDialog(null,
                    "Invalid first number. Please insert another number");
        }

        String n2 = JOptionPane.showInputDialog(null, "Second Number");

        while (!isNumber(n2)) {
            n2 = JOptionPane.showInputDialog(null,
                    "Invalid second number. Please insert another number");
        }

        int r = Integer.parseInt(n1) + Integer.parseInt(n2);

        JOptionPane.showMessageDialog(null, "the result is " + r);
    }
}
