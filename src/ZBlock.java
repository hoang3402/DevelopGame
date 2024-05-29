public class ZBlock extends Block {
    public ZBlock() {
        super(new Position(GameBoard.BLOCK_START_X, 3 + GameBoard.BLOCK_START_Y));

        this.tiles = new Position[][]{
                new Position[]{new Position(0, 0), new Position(0, 1), new Position(1, 1), new Position(1, 2)},
                new Position[]{new Position(0, 2), new Position(1, 1), new Position(1, 2), new Position(2, 1)},
                new Position[]{new Position(1, 0), new Position(1, 1), new Position(2, 1), new Position(2, 2)},
                new Position[]{new Position(0, 1), new Position(1, 0), new Position(1, 1), new Position(2, 0)}
        };
    }
}
