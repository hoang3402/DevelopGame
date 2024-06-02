package Block;

import Helper.BlockIds;
import Helper.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static Core.Setting.TILE_SIZE;

public class SBlock extends Block {
    public SBlock() throws IOException {
        super(new Position(5, 0));

        this.id = BlockIds.S.ordinal();
        this.tiles = new Position[][]{
                new Position[]{new Position(0, 1), new Position(0, 2), new Position(1, 0), new Position(1, 1)},
                new Position[]{new Position(0, 1), new Position(1, 1), new Position(1, 2), new Position(2, 2)},
                new Position[]{new Position(1, 1), new Position(1, 2), new Position(2, 0), new Position(2, 1)},
                new Position[]{new Position(0, 0), new Position(1, 0), new Position(1, 1), new Position(2, 1)}
        };

        var _image = image = ImageIO.read(new File("src/assets/vang.jpg"));
        this.image = _image.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
    }
}
