/**
 * A standalone Java exercise component.
 */
package bullets;

import gameCore.GameObject;
import gameCore.Handler;
import gameCore.ID;
import inputs.InputManager;
import java.awt.*;

public class Bullet extends GameObject {

  protected Handler handler;
  protected int size;
  private boolean[] bb = new boolean[7];

  public Bullet(double x, double y, ID id) {
    super(x, y, id);
  }

  public static int getDelay(ID id) {
    if (id == ID.Bullet1) {
      return 200;
    } else if (id == ID.Bullet2) {
      return 100;
    } else {
      return 300;
    }
  }

  public static int getTemp(ID id) {
    if (id == ID.Bullet1) {
      return 5;
    } else if (id == ID.Bullet2) {
      return 3;
    } else {
      return 10;
    }
  }

  @Override
  public void tick() {
    y += velY;
    x += velX;
    if (this.y <= 0) {
      handler.removeIt(this);
    }
    sync();
  }

  private void sync() {
    for (int i = 0; i < 7; i++) {
      if ((InputManager.bulletPower >= (i + 4)) && !bb[i]) {
        damage += damage / 4d;
        bb[i] = true;
      }
    }
    for (int i = 6; i >= 0; i--) {
      if (InputManager.bulletPower < (i + 4) && bb[i]) {
        damage -= damage / 4d;
        bb[i] = false;
      }
    }
  }

  @Override
  public void render(Graphics g) {}

  @Override
  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, size, size);
  }
}
