package Core;

import Block.Block;
import Helper.Direction;
import Helper.Position;

import java.awt.*;
import java.io.IOException;

public class GameState {

    protected final GameGrid gameGrid;
    // Block drop every [dropInterval] frames
    public int dropInterval = 60;
    private int limitDropInterval = 5;
    private int counter = 0;
    public int blockCounter = 0;
    protected boolean gameOver = false;
    protected int score = 0;
    BlockQueue blockQueue = new BlockQueue();
    Block currentBlock, nextBlock, holdBlock;

    public GameState() throws IOException {
        gameGrid = new GameGrid(22, 12);
        updateBlock();
    }

    public void paint(Graphics2D g) {
        currentBlock.paint(g);
    }

    public boolean blockFits() {
        for (Position position : currentBlock.getPositions()) {
            if (!gameGrid.isEmpty(position)) {
                return false;
            }
        }
        return true;
    }

    private boolean isGameOver() {
        return !(gameGrid.isRowEmpty(0) && gameGrid.isRowEmpty(1));
    }

    public void placeBlock() {
        for (Position position : currentBlock.getPositions()) {
            gameGrid.gameGrid[position.x][position.y] = currentBlock.id;
        }

        addScore(gameGrid.clearFullRows());

        if (isGameOver()) {
            gameOver = true;
        } else {
            updateBlock();
            currentBlock.reset();
        }
    }

    private void addScore(int score) {
        this.score += score;
        this.counter += score;

        if (dropInterval > limitDropInterval && counter >= 10) {
            dropInterval--;
            counter = 0;
        }
    }

    public void rotateCW() {
        currentBlock.rotateCW();
        if (blockFits()) return;

        currentBlock.rotateCCW();
    }

    public void rotateCCW() {
        currentBlock.rotateCCW();
        if (blockFits()) return;

        currentBlock.rotateCW();
    }

    public void move(Direction direction) {
        currentBlock.move(direction);
        if (blockFits()) return;

        currentBlock.move(switch (direction) {
            case Direction.UP -> Direction.DOWN;
            case Direction.DOWN -> Direction.UP;
            case Direction.LEFT -> Direction.RIGHT;
            case Direction.RIGHT -> Direction.LEFT;
        });
        if (direction == Direction.DOWN) placeBlock();
    }

    public void moveDownInstantly() {
        do {
            currentBlock.move(Direction.DOWN);
        } while (blockFits());
        currentBlock.move(Direction.UP);
        placeBlock();
    }

    private void updateBlock() {
        currentBlock = blockQueue.getNextBlockAndUpdate();
        nextBlock = blockQueue.getNextBlock();
    }

    public void setHoldBlock() {
        if (holdBlock == null) {
            holdBlock = currentBlock;
            updateBlock();
        } else {
            Block temp = holdBlock;
            holdBlock = currentBlock;
            currentBlock = temp;
        }

        currentBlock.reset();
    }
}
