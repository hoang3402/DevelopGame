package Block;

import Helper.Position;

public class OBlock extends Block {

    public OBlock() {
        super(new Position(5, 0));

        this.tiles = new Position[][]{
                {new Position(0, 0), new Position(1, 0), new Position(0, 1), new Position(1, 1)},
        };
    }
}
