/**
 * A standalone Java exercise component.
 */
package enemies;

import extras.Images;
import gameCore.Game;
import gameCore.HUD;
import gameCore.Handler;
import gameCore.ID;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.*;

public class Level3Enemy extends Enemy {

  public Level3Enemy(double xt, double yt, ID id, Handler handler, int velX, int velY, HUD hud) {
    super(0, 0, id);
    x = (random.nextInt(20) - 5) * 150;
    if (x <= 0 || x >= Game.w) y = random.nextInt(10) * 85;
    else y = random.nextInt(2) * Game.h;
    image = Images.getImage("enemy3");
    targetX = xt;
    targetY = yt;
    health = 5;
    this.handler = handler;
    this.hud = hud;
    this.velX = velX;
    eggSpeed = 6;
    eggProbability = 10d;
    this.velY = velY;
    score = 80;
  }

  @Override
  public void tick() {
    super.tick();
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
  }

  @Override
  public Rectangle getBounds() {
    return super.getBounds();
  }
}
