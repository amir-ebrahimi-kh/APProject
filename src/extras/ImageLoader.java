/**
 * A standalone Java exercise component.
 */
package extras;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {

  public static BufferedImage loadImage(String path) {
    BufferedImage image = null;
    try {
      image = ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return image;
  }
}
