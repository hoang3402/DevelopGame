public class OBlock extends Block {

    public OBlock() {
        super(new Position(GameBoard.BLOCK_START_X, 4 + GameBoard.BLOCK_START_Y));

        this.tiles = new Position[][]{
                {new Position(0, 0), new Position(1, 0), new Position(0, 1), new Position(1, 1)},
        };
    }
}
