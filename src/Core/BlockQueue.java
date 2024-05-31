package Core;

import Block.*;

import java.util.Random;

public class BlockQueue {

    private final Random random = new Random();
    private final Block[] blocks;

    public BlockQueue() {
        this.blocks = new Block[]{
                new IBlock(),
                new JBlock(),
                new LBlock(),
                new OBlock(),
                new SBlock(),
                new TBlock(),
                new ZBlock()
        };
    }

    public Block nextBlock() {
        return blocks[random.nextInt(blocks.length)];
    }
}
