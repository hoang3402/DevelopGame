import java.awt.*;

public class Ball {
    int x = 0;
    int y = 0;
    int xa = 1;
    int ya = 1;
    private final GameLoop game;

    public Ball(GameLoop game) {
        this.game = game;
    }

    void move() {
        if (x + xa < 0)
            xa = 1;
        if (x + xa > game.getWidth() - 30)
            xa = -1;
        if (y + ya < 0)
            ya = 1;
        if (y + ya > game.getHeight() - 30)
            ya = -1;

        x += xa;
        y += ya;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillOval(x, y, 30, 30);
    }
}
