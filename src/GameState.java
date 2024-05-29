import java.awt.*;

public class GameState {

    BlockQueue blockQueue = new BlockQueue();
    Block currentBlock = blockQueue.nextBlock();

    public GameState() {

    }

    public void paint(Graphics2D g) {
        currentBlock.paint(g);
    }
}
