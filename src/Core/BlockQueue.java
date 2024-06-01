package Core;

import Block.*;

import java.io.IOException;
import java.util.Random;

public class BlockQueue {

    private final Random random = new Random();
    private Block nextBlock;
    protected final Block[] blocks = new Block[]{
            new IBlock(),
            new JBlock(),
            new LBlock(),
            new OBlock(),
            new SBlock(),
            new TBlock(),
            new ZBlock()
    };

    public BlockQueue() throws IOException {
        nextBlock = randomBlock();
    }

    private Block randomBlock() {
        return blocks[random.nextInt(blocks.length)];
    }

    public Block getNextBlock() {
        return nextBlock;
    }

    public Block getNextBlockAndUpdate() {
        Block result = nextBlock;
        nextBlock = randomBlock();
        return result;
    }
}
