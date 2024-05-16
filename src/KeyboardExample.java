import javax.swing.*;
import java.awt.event.KeyListener;

public class KeyboardExample extends JPanel {
    public KeyboardExample() {
        KeyListener listener = new MyKeyListener();
        addKeyListener(listener);
        setFocusable(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mini Tennis");
        KeyboardExample keyboardExample = new KeyboardExample();
        frame.add(keyboardExample);
        frame.setSize(200, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
