/**
 * A standalone Java exercise component.
 */
package bullets;

import extras.Images;
import gameCore.Handler;
import gameCore.ID;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet3 extends Bullet {
  private BufferedImage image;

  public Bullet3(double x, double y, ID id, Handler handler, double xVel) {

    super(x, y, id);
    this.handler = handler;
    velY = -10;
    velX = xVel;
    damage = 5d;
    size = 5;
    image = Images.getImage("goldbul");
  }

  @Override
  public void tick() {
    super.tick();
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(image, (int) x - 17, (int) y - 10, null);
  }

  @Override
  public Rectangle getBounds() {
    return super.getBounds();
  }
}
