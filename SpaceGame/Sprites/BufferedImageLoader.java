package SpaceGame.Sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImageLoader {
    private BufferedImage image;

    public BufferedImage loadImage(String path) throws IOException {
        image  = ImageIO.read(BufferedImageLoader.class.getResource(path));
        return image;
    }
}
