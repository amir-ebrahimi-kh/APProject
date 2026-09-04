/**
 * A standalone Java exercise component.
 */
package enemies;

import gameCore.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {

  public static int size = 84;
  public boolean reachedFirstDest = false;
  public boolean reachedDest = false;
  public double targetX;
  public double targetY;
  public double theta;
  public boolean isLeader = false;
  public boolean isAlive;
  protected BufferedImage image;
  protected Handler handler;
  protected HUD hud;
  protected int score;
  protected Random random = new Random();
  protected TerroristGroup tg;
  protected boolean isInTg = false;
  protected int eggSpeed;
  protected double eggProbability;
  private int counter;

  public Enemy(double x, double y, ID id) {
    super(x, y, id);
    this.x = x;
    isAlive = true;
    this.y = y;
    this.id = id;
  }

  public void setTg(TerroristGroup tg) {
    this.tg = tg;
    isInTg = true;
  }

  public void tick() {
    if (isAlive) {
      if (!reachedFirstDest) goTo(targetX, targetY, 7d);
      collision();
      counter++;
      if (counter >= 60) {
        counter = 0;
        throwEgg();
      }
      x += velX;
      y += velY;
    }
  }

  public void render(Graphics g) {
    g.drawImage(image, (int) x, (int) y, null);
  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, size + 18, size);
  }

  public void goTo(double Xtarget, double Ytarget, double speed) {
    if (!reachedDest) {
      double diffX = x - Xtarget;
      double diffY = y - Ytarget;
      double distance = Math.sqrt(diffX * diffX + diffY * diffY);
      if (distance >= 5d) {
        velX = ((-speed / distance) * diffX);
        velY = ((-speed / distance) * diffY);
      } else {
        reachedDest = true;
        reachedFirstDest = true;
        velY = 0d;
        velX = 0d;
      }
    }
  }

  public void attackPlayer(double Xtarget, double Ytarget, double speed) {
    if (!reachedDest) {
      double diffX = x - Xtarget;
      double diffY = y - Ytarget;
      double distance = Math.sqrt(diffX * diffX + diffY * diffY);
      if (distance >= 5d) {
        velX = ((-speed / distance) * diffX);
        velY = ((-speed / distance) * diffY);
      } else {
        reachedDest = true;
        reachedFirstDest = true;
        isLeader = false;
        velY = 0d;
        velX = 0d;
      }
    }
  }

  protected void collision() {
    for (int i = 0; i < handler.size(); i++) {
      GameObject temp = handler.getObjectIn(i);
      if (temp.getId() == ID.Bullet) {
        if (getBounds().intersects(temp.getBounds())) {
          health -= temp.getDamage();
          handler.removeIt(i);
        }
      }
      if (!Player.hasShield) {
        if (temp.getId() == ID.Player) {
          if (getBounds().intersects(temp.getBounds())) {
            HUD.HEALTH--;
            Spawn.progress += score;
            isAlive = false;
            handler.removeIt(this);
            if (isInTg) {
              tg.remove(this);
            }
            handler.removeIt(temp);
            handler.respawnPlayer();
          }
        }
      }
    }

    if (health <= 0) {
      hud.setScore(hud.getScore() + score);
      hud.addedScore(score);
      isAlive = false;
      throwCoin();
      throwPower();
      if (isInTg) {
        tg.remove(this);
      }
      handler.removeIt(this);
    }
  }

  private void throwPower() {
    if (random.nextInt(33) == 16) {
      if (random.nextInt(2) == 0)
        handler.addObject(new PowerUpHeat(x + (size / 2), y + (size / 2), ID.PowerUp, handler));
      else
        handler.addObject(new PowerUpBullet(x + (size / 2), y + (size / 2), ID.PowerUp, handler));
      return;
    }
    if (random.nextInt(33) == 18)
      handler.addObject(new PowerUpNew(x + (size / 2), y + (size / 2), ID.PowerUp, handler));
  }

  private void throwEgg() {
    int range = (int) ((1d / eggProbability) * 100d);
    if (random.nextInt(range) == range / 2) {
      handler.addObject(
          new Egg(x + (size / 2) + 7, y + (size / 2) + 30, ID.Egg, handler, 0, eggSpeed, hud));
    }
  }

  private void throwCoin() {
    if (random.nextInt(16) == 6)
      handler.addObject(new Coin(x + (size / 2), y + (size / 2), ID.Coin, handler));
  }
}
