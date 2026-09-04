/**
 * A standalone Java exercise component.
 */
package enemies;

import extras.Images;
import gameCore.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Coin extends GameObject {
  private Handler handler;
  private BufferedImage image;

  public Coin(double x, double y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
    velY = 3;
    health = 1;
    image = Images.getImage("coin");
  }

  @Override
  public void tick() {
    y += velY;
    collision();
    if (y >= Game.h) handler.removeIt(this);
  }

  private void collision() {
    for (int i = 0; i < handler.size(); i++) {
      GameObject temp = handler.getObjectIn(i);
      if (temp.getId() == ID.Player) {
        if (getBounds().intersects(temp.getBounds())) {
          HUD.coins++;
          handler.removeIt(this);
        }
      }
      if (temp.getId() == ID.Bullet) {
        if (getBounds().intersects(temp.getBounds())) {
          health -= temp.getDamage();
        }
      }
    }
    if (health <= 0) handler.removeIt(this);
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(image, (int) x - 8, (int) y - 8, null);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 15, 15);
  }
}
