import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Snake extends JPanel implements KeyListener {

    Tile Head;
    ArrayList<Tile> Body;
    Image headDown, headLeft, headRight, headUp, tail;
    Direction direction;
    Game game;

    private void loadImage() throws IOException {
        var _headDown = ImageIO.read(new File("src/assets/headDown.png"));
        var _headLeft = ImageIO.read(new File("src/assets/headLeft.png"));
        var _headRight = ImageIO.read(new File("src/assets/headRight.png"));
        var _headUp = ImageIO.read(new File("src/assets/headUp.png"));
        var _tail = ImageIO.read(new File("src/assets/tail.png"));

        headDown = _headDown.getScaledInstance(Main.BLOCK_SIZE, Main.BLOCK_SIZE, Image.SCALE_SMOOTH);
        headLeft = _headLeft.getScaledInstance(Main.BLOCK_SIZE, Main.BLOCK_SIZE, Image.SCALE_SMOOTH);
        headRight = _headRight.getScaledInstance(Main.BLOCK_SIZE, Main.BLOCK_SIZE, Image.SCALE_SMOOTH);
        headUp = _headUp.getScaledInstance(Main.BLOCK_SIZE, Main.BLOCK_SIZE, Image.SCALE_SMOOTH);
        tail = _tail.getScaledInstance(Main.BLOCK_SIZE, Main.BLOCK_SIZE, Image.SCALE_SMOOTH);
    }

    public Snake(int locationX, int locationY, Game game) {
        direction = Direction.RIGHT;
        this.game = game;
        Head = new Tile(locationX, locationY);
        Body = new ArrayList<>();
        try {
            loadImage();
        } catch (IOException e) {
            System.out.println("Failed to load image " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void move() {

        if (game.collides(Head, game.fruitTile)) {
            System.out.println("Snake ate fruit: " + Head.x + ", " + Head.y);
            game.tick -= (int) (double) (game.score / 10);
            Body.add(new Tile(game.fruitTile.x, game.fruitTile.y));
            game.score++;
            game.placeFruit();
        }

        // move snake body
        for (int i = Body.size() - 1; i >= 0; i--) {
            Tile current = Body.get(i);

            if (i == 0) {
                current.x = Head.x;
                current.y = Head.y;
            } else {
                Tile prev = Body.get(i - 1);
                current.x = prev.x;
                current.y = prev.y;
            }
        }

        switch (direction) {
            case UP:
                Head.y -= 1;
                break;
            case DOWN:
                Head.y += 1;
                break;
            case LEFT:
                Head.x -= 1;
                break;
            case RIGHT:
                Head.x += 1;
                break;
        }

        for (Tile tile : Body) {
            if (game.collides(Head, tile)) {
                game.gameOver();
            }
        }

        if (Head.x < 0 || Head.x >= Main.WIDTH / Main.BLOCK_SIZE ||
                Head.y < 0 || Head.y >= Main.HEIGHT / Main.BLOCK_SIZE) {
            game.gameOver();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        switch (direction) {
            case UP:
                g.drawImage(headUp, Head.x * Main.BLOCK_SIZE, Head.y * Main.BLOCK_SIZE, this);
                break;
            case DOWN:
                g.drawImage(headDown, Head.x * Main.BLOCK_SIZE, Head.y * Main.BLOCK_SIZE, this);
                break;
            case LEFT:
                g.drawImage(headLeft, Head.x * Main.BLOCK_SIZE, Head.y * Main.BLOCK_SIZE, this);
                break;
            case RIGHT:
                g.drawImage(headRight, Head.x * Main.BLOCK_SIZE, Head.y * Main.BLOCK_SIZE, this);
                break;
        }

        for (Tile tile : Body) {
            g.drawImage(tail, tile.x * Main.BLOCK_SIZE, tile.y * Main.BLOCK_SIZE, this);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (game.lock) return;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                direction = direction != Direction.DOWN ? Direction.UP : Direction.DOWN;
                game.lock = true;
                break;
            case KeyEvent.VK_DOWN:
                direction = direction != Direction.UP ? Direction.DOWN : Direction.UP;
                game.lock = true;
                break;
            case KeyEvent.VK_LEFT:
                direction = direction != Direction.RIGHT ? Direction.LEFT : Direction.RIGHT;
                game.lock = true;
                break;
            default:
                direction = direction != Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
                game.lock = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
