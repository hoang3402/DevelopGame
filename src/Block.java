import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Block {

    Position startOffset;
    Position currentOffset;
    Position[][] tiles;
    int state = 0;

    public Block(Position position) {
        startOffset = position;
        currentOffset = position;
    }

    public void paint(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        for (Position tile : tiles[state]) {
            graphics2D.fillRect(
                    tile.x * Main.TILE_SIZE + currentOffset.x,
                    tile.y * Main.TILE_SIZE + currentOffset.y,
                    Main.TILE_SIZE, Main.TILE_SIZE
            );
        }
    }

    public void rotateCW() {
        System.out.println("Rotate cw");
        this.state = (this.state + 1) % this.tiles.length;
    }

    public void rotateCCW() {
        this.state = (this.state - 1 + this.tiles.length) % this.tiles.length;
    }

    public List<Position> getPositions() {
        List<Position> result = new ArrayList<>();
        for (Position tile : this.tiles[state]) {
            result.add(new Position(
                    tile.x * Main.TILE_SIZE + currentOffset.x,
                    tile.y * Main.TILE_SIZE + currentOffset.y
            ));
        }
        return result;
    }

    public void move(Direction direction) {
        int x = 0, y = 0;
        switch (direction) {
            case UP -> {
                return;
            }
            case DOWN -> y = 1;
            case LEFT -> x = -1;
            case RIGHT -> x = 1;
        }

        this.currentOffset = new Position(
                this.currentOffset.x + x * Main.TILE_SIZE,
                this.currentOffset.y + y * Main.TILE_SIZE
        );
    }

    public void reset() {
        this.currentOffset = this.startOffset;
        this.state = 0;
    }

    
}
