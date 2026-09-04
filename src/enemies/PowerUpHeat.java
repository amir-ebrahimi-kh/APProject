/**
 * A standalone Java exercise component.
 */
package enemies;

import extras.Images;
import gameCore.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PowerUpHeat extends GameObject {
  private BufferedImage image;
  private Handler handler;

  public PowerUpHeat(double x, double y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
    velY = 3;
    image = Images.getImage("powerup_heat");
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
          HeatSystem.maxTemp += 5;
          handler.removeIt(this);
        }
      }
    }
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(image, (int) x - 3, (int) y - 3, null);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 15, 15);
  }
}
