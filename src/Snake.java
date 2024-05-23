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

        if (game.collides(Head, game.fruitTile)) {
            System.out.println("Head ate fruit: " + Head.x + ", " + Head.y);
            Body.add(new Tile(Head.x, Head.y));
            game.score++;
            game.placeFruit();
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
            if (tile != null) {
                g.drawImage(tail, tile.x * Main.BLOCK_SIZE, tile.y * Main.BLOCK_SIZE, this);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                direction = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                direction = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                direction = Direction.LEFT;
                break;
            default:
                direction = Direction.RIGHT;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
