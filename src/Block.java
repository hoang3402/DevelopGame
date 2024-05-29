import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Block {

    Position startOffset = new Position(GameBoard.BLOCK_START_X, GameBoard.BLOCK_START_Y);
    Position currentOffset;
    Position[][] tiles;
    int state = 0;

    public Block() {
        currentOffset = startOffset;
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
                    tile.x + currentOffset.x,
                    tile.y + currentOffset.y
            ));
        }
        return result;
    }

    public void move(Position position) {
        this.currentOffset = new Position(
                this.currentOffset.x + position.x * Main.TILE_SIZE,
                this.currentOffset.y + position.y * Main.TILE_SIZE
        );
    }

    public void reset() {
        this.currentOffset = this.startOffset;
        this.state = 0;
    }
}
