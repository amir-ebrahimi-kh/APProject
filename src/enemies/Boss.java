/**
 * A standalone Java exercise component.
 */
package enemies;

import extras.Images;
import gameCore.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Boss extends GameObject {

  public static int size = 400;
  public boolean reachedFirstDest = false;
  public boolean reachedDest = false;
  public double targetX;
  public double targetY;
  public boolean isAlive;
  protected Handler handler;
  protected HUD hud;
  protected int score;
  protected Random random = new Random();
  protected double mainSpeed;
  protected double eggProbability;
  private double[] velx = new double[8];
  private double[] vely = new double[8];
  private int radius = 200;
  private int counter2;
  private BufferedImage[] anim = {
    Images.getImage("boss1"),
    Images.getImage("boss2"),
    Images.getImage("boss3"),
    Images.getImage("boss4"),
    Images.getImage("boss5"),
    Images.getImage("boss6"),
    Images.getImage("boss7"),
    Images.getImage("boss8")
  };
  private int animIndex = 0;
  private int counter = 0;

  public Boss(
      double x,
      double y,
      ID id,
      double mainSpeed,
      double health,
      int eggSpeed,
      double eggProbability,
      Handler handler,
      HUD hud) {
    super(800, -600, id);
    targetX = x;
    targetY = y;
    this.mainSpeed = mainSpeed;
    this.health = health;
    this.eggProbability = eggProbability;
    this.handler = handler;
    this.hud = hud;
    this.score = (int) health * 2;
    isAlive = true;
    velx[6] = 0;
    vely[6] = -eggSpeed;
    velx[7] = eggSpeed / Math.sqrt(2);
    vely[7] = -velx[7];
    velx[0] = eggSpeed;
    vely[0] = 0;
    velx[1] = eggSpeed / Math.sqrt(2);
    vely[1] = velx[1];
    velx[2] = 0;
    vely[2] = eggSpeed;
    velx[3] = -eggSpeed / Math.sqrt(2);
    vely[3] = -velx[3];
    velx[4] = -eggSpeed;
    vely[4] = 0;
    velx[5] = -eggSpeed / Math.sqrt(2);
    vely[5] = velx[5];
  }

  public void tick() {
    if (reachedFirstDest) {
      counter2++;
      if (counter2 >= 30) {
        counter2 = 0;
        throwEgg();
      }
    }
    counter++;
    if (counter >= 10) {
      animIndex++;
      if (animIndex > 7) {
        animIndex = 0;
      }
      counter = 0;
    }
    if (isAlive) {
      if (!reachedFirstDest) goTo(targetX, targetY, 5d);
      collision();
      x += velX;
      y += velY;
      if (reachedDest && reachedFirstDest) {
        reachedDest = false;
        targetX = random.nextInt(Game.w - 400);
        targetY = random.nextInt(Game.h - 500);
      }
      if (reachedFirstDest && !reachedDest) {
        goTo(targetX, targetY, mainSpeed);
      }
    }
  }

  public void render(Graphics g) {
    g.setColor(Color.WHITE);
    g.drawRect(200, Game.h - 50, score / 2, 25);
    g.setColor(Color.GRAY);
    g.fillRect(200, Game.h - 50, score / 2, 25);
    g.setColor(Color.RED);
    g.fillRect(200, Game.h - 50, (int) health, 25);
    g.drawImage(anim[animIndex], (int) x - 65, (int) y - 55, null);
  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, size - 25, size - 5);
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
        velX = 0;
        velY = 0;
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
            this.health -= 20;
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
      throwPower();
      handler.removeIt(this);
    }
  }

  private void throwPower() {
    for (int i = 0; i < 5; i++) {
      int prob = random.nextInt(3);
      if (prob == 0)
        handler.addObject(new PowerUpHeat(x + 187 + 50, y + 197 + i * 50, ID.PowerUp, handler));
      if (prob == 1)
        handler.addObject(new PowerUpBullet(x + 187, y + 197 + i * 50, ID.PowerUp, handler));
      if (prob == 2)
        handler.addObject(new PowerUpNew(x + 187 - 50, y + 197 + i * 50, ID.PowerUp, handler));
    }
  }

  private void throwEgg() {
    int range = (int) ((1d / eggProbability) * 100d);
    for (int i = 0; i < 8; i++) {
      if (random.nextInt(range) == range / 2) {
        handler.addObject(
            new Egg(
                x + 187 + (radius * Math.cos(45 * i * Math.PI / 180)),
                y + 197 + (radius * Math.sin(45 * i * Math.PI / 180)),
                ID.Egg,
                handler,
                velx[i],
                vely[i],
                hud));
      }
    }
  }
}
