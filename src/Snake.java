import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;


public class Snake extends JPanel implements KeyListener {

    Tile Head;
    Tile[] Body;
    Image headDown, headLeft, headRight, headUp, tail;
    Direction direction;

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

    public Snake(int locationX, int locationY) {
        direction = Direction.RIGHT;
        Head = new Tile(locationX, locationY);
        Body = new Tile[100];
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
                Head.y -= Main.BLOCK_SIZE;
                break;
            case DOWN:
                Head.y += Main.BLOCK_SIZE;
                break;
            case LEFT:
                Head.x -= Main.BLOCK_SIZE;
                break;
            case RIGHT:
                Head.x += Main.BLOCK_SIZE;
                break;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        switch (direction) {
            case UP:
                g.drawImage(headUp, Head.x, Head.y, this);
                break;
            case DOWN:
                g.drawImage(headDown, Head.x, Head.y, this);
                break;
            case LEFT:
                g.drawImage(headLeft, Head.x, Head.y, this);
                break;
            case RIGHT:
                g.drawImage(headRight, Head.x, Head.y, this);
                break;
        }

        for (Tile tile : Body) {
            if (tile != null) {
                g.drawImage(tail, tile.x, tile.y, this);
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
