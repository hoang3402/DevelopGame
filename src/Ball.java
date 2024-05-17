import java.awt.*;

public class Ball {
    private static final int DIAMETER = 30;
    int x = 0;
    int y = 0;
    int xa = 1;
    int ya = 1;
    private final GameLoop game;

    public Ball(GameLoop game) {
        this.game = game;
    }

    void move() {
        boolean changeDirection = true;
        if (x + xa < 0)
            xa = game.speed;
        else if (x + xa > game.getWidth() - DIAMETER)
            xa = -game.speed;
        else if (y + ya < 0 || y + ya > game.getHeight() - DIAMETER)
            game.gameOver(); // can change to gameWin
        else if (collision() != 0) {
            ya = collision() == 1 ? game.speed : -game.speed;
            game.speed++;
        } else
            changeDirection = false;

        if (changeDirection)
            MyAudio.play("BALL");

        x = x + xa;
        y = y + ya;
    }

    // 0 khong cham, 1 cham o tren, 2 cham o duoi
    private int collision() {
        if (game.racquet.getBounds().intersects(getBounds()))
            return 2;
        if (game.racquetEnemy.getBounds().intersects(getBounds()))
            return 1;
        return 0;
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}
