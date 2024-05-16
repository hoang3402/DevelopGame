import java.awt.*;
import java.awt.event.KeyEvent;

public class Racquet {
    int x = 0;
    int xa = 0;
    private final GameLoop game;

    public Racquet(GameLoop game) {
        this.game = game;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - 60)
            x += xa;
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, 330, 60, 10);
    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = -1;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = 1;
    }
}
