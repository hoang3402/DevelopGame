package Block;

import Core.GameManager;
import Helper.Direction;
import Helper.Position;

import java.awt.*;
import java.util.ArrayList;

import static Core.Setting.TILE_SIZE;

public class Block {

    public Position[][] tiles;
    public int id;
    Position startOffset;
    Position currentOffset;
    int state = 0;
    public Image image;

    public Block(Position position) {
        startOffset = position;
        currentOffset = position;
    }

    public void paint(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);


        for (Position tile : tiles[state]) {

            if (image != null) {
                graphics2D.drawImage(image,
                        (tile.x + currentOffset.x) * TILE_SIZE + GameManager.LEFT_X,
                        (tile.y + currentOffset.y) * TILE_SIZE,
                        null);
            } else {
                System.out.println("something is wrong");
                graphics2D.fillRect(
                        (tile.x + currentOffset.x) * TILE_SIZE + GameManager.LEFT_X,
                        (tile.y + currentOffset.y) * TILE_SIZE,
                        TILE_SIZE, TILE_SIZE
                );
            }
        }
    }

    public void rotateCW() {
        // System.out.println("Rotate cw");
        this.state = (this.state + 1) % this.tiles.length;
    }

    public void rotateCCW() {
        // System.out.println("Rotate cww");
        this.state = (this.state - 1 + this.tiles.length) % this.tiles.length;
    }

    public java.util.List<Position> getPositions() {
        java.util.List<Position> result = new ArrayList<>();
        for (Position tile : this.tiles[state]) {
            result.add(new Position(
                    tile.x + currentOffset.x,
                    tile.y + currentOffset.y
            ));
        }
        return result;
    }

    public void move(Direction direction) {
        int x = 0, y = 0;
        switch (direction) {
            case Direction.UP -> y = -1;
            case Direction.DOWN -> y = 1;
            case Direction.LEFT -> x = -1;
            case Direction.RIGHT -> x = 1;
        }

        this.currentOffset.x += x;
        this.currentOffset.y += y;
    }

    public void reset() {
        this.currentOffset.x = 5;
        this.currentOffset.y = 0;
        this.state = 0;
    }
}
