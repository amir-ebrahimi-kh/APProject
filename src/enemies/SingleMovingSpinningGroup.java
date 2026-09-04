/**
 * A standalone Java exercise component.
 */
package enemies;

import gameCore.Game;
import gameCore.HUD;
import gameCore.Handler;
import gameCore.ID;
import java.util.Random;

public class SingleMovingSpinningGroup extends SpinningGroup {

  protected boolean isLeader = true;
  private boolean movingToNext = false;
  private double centerSpeed;
  private double xx, yy;
  private Random random = new Random();

  public SingleMovingSpinningGroup(
      double x,
      double y,
      ID id,
      int size,
      double radius,
      double angularSpeed,
      double centerSpeed,
      Handler handler,
      HUD hud) {
    super(x, y, id, size, radius, angularSpeed, handler, hud);
    this.centerSpeed = centerSpeed;
  }

  @Override
  public void tick() {
    super.tick();
    if (isLeader) {
      if (moving && !movingToNext) {
        movingToNext = true;
        xx = random.nextInt(Game.w);
        yy = random.nextInt(Game.h);
      }
      if (movingToNext) goTo(xx, yy, centerSpeed);
    }
  }

  public void goTo(double Xtarget, double Ytarget, double speed) {

    double diffX = x - Xtarget;
    double diffY = y - Ytarget;
    double distance = Math.sqrt(diffX * diffX + diffY * diffY);
    if (distance >= 5d) {
      velX = ((-speed / distance) * diffX);
      velY = ((-speed / distance) * diffY);
    } else {
      movingToNext = false;
      velY = 0d;
      velX = 0d;
    }
  }
}
