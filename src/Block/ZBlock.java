package Block;

import Core.Main;
import Helper.BlockIds;
import Helper.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ZBlock extends Block {
    public ZBlock() throws IOException {
        super(new Position(5, 0));

        this.id = BlockIds.Z.ordinal();
        this.tiles = new Position[][]{
                new Position[]{new Position(0, 0), new Position(0, 1), new Position(1, 1), new Position(1, 2)},
                new Position[]{new Position(0, 2), new Position(1, 1), new Position(1, 2), new Position(2, 1)},
                new Position[]{new Position(1, 0), new Position(1, 1), new Position(2, 1), new Position(2, 2)},
                new Position[]{new Position(0, 1), new Position(1, 0), new Position(1, 1), new Position(2, 0)}
        };

        var _image = ImageIO.read(new File("src/assets/xanh_la.jpg"));
        this.image = _image.getScaledInstance(Main.TILE_SIZE, Main.TILE_SIZE, Image.SCALE_SMOOTH);
    }
}
