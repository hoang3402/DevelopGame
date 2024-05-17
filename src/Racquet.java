import java.awt.*;

enum RacquetDirection {
    LEFT, RIGHT
}

public class Racquet {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 10;
    int x, xa, y;
    private final GameLoop game;

    public Racquet(GameLoop game, int x, int xa, int y) {
        this.game = game;
        this.x = x;
        this.xa = xa;
        this.y = y;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - WIDTH)
            x = x + xa;
    }

    public void paint(Graphics2D g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public void keyReleased() {
        xa = 0;
    }

    public void keyPressed(RacquetDirection direction) {
        if (direction == RacquetDirection.LEFT)
            xa = -game.speed;
        if (direction == RacquetDirection.RIGHT)
            xa = game.speed;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public int getTopY() {
        return y;
    }
}
