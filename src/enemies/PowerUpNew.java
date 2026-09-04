/**
 * A standalone Java exercise component.
 */
package enemies;

import extras.Images;
import gameCore.Game;
import gameCore.GameObject;
import gameCore.Handler;
import gameCore.ID;
import inputs.InputManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class PowerUpNew extends GameObject {

  private Random r = new Random();
  private Handler handler;
  private int type;
  private BufferedImage image;

  public PowerUpNew(double x, double y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
    velY = 3;
    this.type = r.nextInt(3);
    switch (type) {
      case 0:
        image = Images.getImage("silver");
        break;
      case 1:
        image = Images.getImage("bronze");
        break;
      case 2:
        image = Images.getImage("gold");
        break;
    }
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
          if (!(InputManager.type == this.type))
            InputManager.type = this.type; // 0 == 1 \\ 1 == 2 \\ 2 == 3\\
          else InputManager.bulletPower++;
          handler.removeIt(this);
        }
      }
    }
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(image, (int) x - 1, (int) y - 1, null);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, 18, 28);
  }
}
