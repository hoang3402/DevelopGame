package Block;

import Core.Main;
import Helper.BlockIds;
import Helper.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class IBlock extends Block {

    public IBlock() throws IOException {
        super(new Position(5, 0));

        this.id = BlockIds.I.ordinal();
        this.tiles = new Position[][]{
                new Position[]{new Position(1, 0), new Position(1, 1), new Position(1, 2), new Position(1, 3)},
                new Position[]{new Position(0, 2), new Position(1, 2), new Position(2, 2), new Position(3, 2)},
                new Position[]{new Position(2, 0), new Position(2, 1), new Position(2, 2), new Position(2, 3)},
                new Position[]{new Position(0, 1), new Position(1, 1), new Position(2, 1), new Position(3, 1)}
        };

        var _image = image = ImageIO.read(new File("src/assets/cam.jpg"));
        this.image = _image.getScaledInstance(Main.TILE_SIZE, Main.TILE_SIZE, Image.SCALE_SMOOTH);
    }
}
