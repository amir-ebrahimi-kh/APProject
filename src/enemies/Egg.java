/**
 * A standalone Java exercise component.
 */
package enemies;

import extras.Images;
import gameCore.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Egg extends GameObject {

  public static int size = 5;
  private BufferedImage image;
  private Handler handler;

  public Egg(double x, double y, ID id, Handler handler, double velX, double velY, HUD hud) {
    super(x, y, id);
    this.velY = velY;
    this.velX = velX;
    this.handler = handler;
    image = Images.getImage("pinkbul");
  }

  @Override
  public void tick() {
    x += velX;
    y += velY;
    collision();
    if (y >= Game.h - size || y <= 0 || x <= 0 || x >= Game.w - size) {
      handler.removeIt(this);
    }
  }

  private void collision() {
    if (!Player.hasShield) {
      for (int i = 0; i < handler.size(); i++) {
        GameObject temp = handler.getObjectIn(i);
        if (temp.getId() == ID.Player) {
          if (getBounds().intersects(temp.getBounds())) {
            HUD.HEALTH--;
            handler.removeIt(this);
            handler.removeIt(temp);
            handler.respawnPlayer();
          }
        }
      }
    }
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(image, (int) x - 12, (int) y - 12, null);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, size, size);
  }
}
