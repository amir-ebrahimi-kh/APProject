/**
 * A standalone Java exercise component.
 */
package bullets;

import extras.Images;
import gameCore.Handler;
import gameCore.ID;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet2 extends Bullet {

  private BufferedImage image;

  public Bullet2(int x, int y, ID id, Handler handler, double xVel) {
    super(x, y, id);
    this.handler = handler;
    velY = -10;
    velX = xVel;
    damage = 2d;
    size = 5;
    image = Images.getImage("redbul");
  }

  @Override
  public void tick() {
    super.tick();
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(image, (int) x - 15, (int) y - 15, null);
  }

  @Override
  public Rectangle getBounds() {
    return super.getBounds();
  }
}
