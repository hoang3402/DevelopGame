package Core;

import Block.*;

import java.util.Random;

public class BlockQueue {

    private final Random random = new Random();
    private Block nextBlock;
    private final Block[] blocks = new Block[]{
            new IBlock(),
            new JBlock(),
            new LBlock(),
            new OBlock(),
            new SBlock(),
            new TBlock(),
            new ZBlock()
    };

    public BlockQueue() {
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
