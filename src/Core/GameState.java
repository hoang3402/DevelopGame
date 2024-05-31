package Core;

import Block.Block;
import Helper.Direction;
import Helper.Position;

import java.awt.*;

public class GameState {

    protected final GameGrid gameGrid;

    // Block drop every [dropInterval] frames
    public int dropInterval = 15;
    public int blockCounter = 0;
    BlockQueue blockQueue = new BlockQueue();
    Block currentBlock;
    protected boolean gameOver = false;

    public GameState() {
        gameGrid = new GameGrid(22, 12);
        currentBlock = blockQueue.getNextBlockAndUpdate();
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
            gameGrid.gameGrid[position.x][position.y] = 1;
        }

        gameGrid.clearFullRows();

        if (isGameOver()) {
            gameOver = true;
        } else {
            currentBlock = blockQueue.getNextBlockAndUpdate();
            currentBlock.reset();
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
}
