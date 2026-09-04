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

public class Level2Enemy extends Enemy {

  public Level2Enemy(double xt, double yt, ID id, Handler handler, int velX, int velY, HUD hud) {
    super(0, 0, id);
    x = (random.nextInt(20) - 5) * 150;
    if (x <= 0 || x >= Game.w) y = random.nextInt(10) * 85;
    else y = random.nextInt(2) * Game.h;
    image = Images.getImage("enemy2");
    targetX = xt;
    targetY = yt;
    health = 3;
    this.velX = velX;
    this.velY = velY;
    this.handler = handler;
    eggSpeed = 3;
    eggProbability = 5d;
    this.hud = hud;
    score = 40;
  }

  public void tick() {
    super.tick();
  }

  public void render(Graphics g) {
    super.render(g);
  }

  public Rectangle getBounds() {
    return super.getBounds();
  }
}
