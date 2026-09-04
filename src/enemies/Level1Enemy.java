/**
 * A standalone Java exercise component.
 */
package enemies;

import extras.Images;
import gameCore.Game;
import gameCore.HUD;
import gameCore.Handler;
import gameCore.ID;
import java.awt.*;

public class Level1Enemy extends Enemy {

  public Level1Enemy(
      double xt, double yt, ID id, Handler handler, double velX, double velY, HUD hud) {
    super(0, 0, id);
    x = (random.nextInt(20) - 5) * 150;
    if (x <= 0 || x >= Game.w) y = random.nextInt(10) * 85;
    else y = random.nextInt(2) * Game.h;
    targetX = xt;
    image = Images.getImage("enemy1");
    targetY = yt;
    health = 2;
    this.velX = velX;
    this.velY = velY;
    this.handler = handler;
    this.hud = hud;
    score = 20;
    eggSpeed = 3;
    eggProbability = 5d;
  }
}
