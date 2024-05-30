import java.awt.*;

public class GameState {

    BlockQueue blockQueue = new BlockQueue();
    Block currentBlock = blockQueue.nextBlock();

    // Block drop every 60 frames
    public int dropInterval = 60;
    public int blockCounter = 0;

    public GameState() {

    }

    public void paint(Graphics2D g) {
        currentBlock.paint(g);
    }
}
