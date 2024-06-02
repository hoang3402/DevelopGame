package Block;

import Helper.BlockIds;
import Helper.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static Core.Setting.TILE_SIZE;

public class OBlock extends Block {

    public OBlock() throws IOException {
        super(new Position(5, 0));

        this.id = BlockIds.O.ordinal();
        this.tiles = new Position[][]{
                {new Position(0, 0), new Position(1, 0), new Position(0, 1), new Position(1, 1)},
        };

        var _image = image = ImageIO.read(new File("src/assets/tim.jpg"));
        this.image = _image.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH);
    }
}
