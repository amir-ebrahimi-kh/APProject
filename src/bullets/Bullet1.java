/**
 * A standalone Java exercise component.
 */
package bullets;

import extras.Images;
import gameCore.Handler;
import gameCore.ID;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet1 extends Bullet {

  private BufferedImage image;

  public Bullet1(double x, double y, ID id, Handler handler, double xVel) {
    super(x, y, id);
    this.handler = handler;
    velY = -10;
    velX = xVel;
    damage = 1d;
    size = 5;
    image = Images.getImage("bluebul");
  }

  public void tick() {
    super.tick();
  }

  public void render(Graphics g) {
    g.drawImage(image, (int) x - 15, (int) y - 15, null);
  }

  public Rectangle getBounds() {
    return super.getBounds();
  }
}
