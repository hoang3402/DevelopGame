package Block;

import Helper.Position;

public class JBlock extends Block {
    public JBlock() {
        super(new Position(5, 0));

        this.tiles = new Position[][]{
                new Position[]{new Position(0, 0), new Position(1, 0), new Position(1, 1), new Position(1, 2)},
                new Position[]{new Position(0, 1), new Position(0, 2), new Position(1, 1), new Position(2, 1)},
                new Position[]{new Position(1, 0), new Position(1, 1), new Position(1, 2), new Position(2, 2)},
                new Position[]{new Position(0, 1), new Position(1, 1), new Position(2, 0), new Position(2, 1)}
        };
    }
}
