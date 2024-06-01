package Block;

import Helper.Position;

public class TBlock extends Block {
    public TBlock() {
        super(new Position(5, 0));

        this.id = 6;
        this.tiles = new Position[][]{
                new Position[]{new Position(0, 1), new Position(1, 0), new Position(1, 1), new Position(1, 2)},
                new Position[]{new Position(0, 1), new Position(1, 1), new Position(1, 2), new Position(2, 1)},
                new Position[]{new Position(1, 0), new Position(1, 1), new Position(1, 2), new Position(2, 1)},
                new Position[]{new Position(0, 1), new Position(1, 0), new Position(1, 1), new Position(2, 1)}
        };
    }
}
