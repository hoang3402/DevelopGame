package Core;

import Helper.Position;

public class GameGrid {

    private final int rows;
    private final int columns;
    public int[][] gameGrid;

    public GameGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        gameGrid = new int[columns][rows];
    }

    public boolean isInside(Position position) {
        return position.x >= 0 && position.x < columns && position.y >= 0 && position.y < rows;
    }

    public boolean isEmpty(Position position) {
        return isInside(position) && gameGrid[position.x][position.y] == 0;
    }

    public boolean isRowFull(int y) {
        for (int x = 0; x < columns; x++) {
            if (gameGrid[x][y] == 0) return false;
        }
        return true;
    }

    public boolean isRowEmpty(int y) {
        for (int x = 0; x < columns; x++) {
            if (gameGrid[x][y] != 0) return false;
        }
        return true;
    }

    public void clearRow(int y) {
        for (int x = 0; x < columns; x++) {
            gameGrid[x][y] = 0;
        }
    }

    public void moveDown(int y, int numRows) {
        for (int x = 0; x < columns; x++) {
            gameGrid[x][y + numRows] = gameGrid[x][y];
            gameGrid[x][y] = 0;
        }
    }

    public void clearFullRows() {
        int clearCount = 0;

        for (int y = rows - 1; y >= 0; y--) {
            if (isRowFull(y)) {
                clearRow(y);
                clearCount++;
            } else if (clearCount > 0) {
                moveDown(y, clearCount);
            }
        }
    }
}
