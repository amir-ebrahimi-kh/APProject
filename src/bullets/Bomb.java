/**
 * A standalone Java exercise component.
 */
package bullets;

import extras.AudioPlayer;
import extras.Images;
import gameCore.*;
import gameCore.Menu;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Bomb extends GameObject {

  private static BufferedImage[] anim = {
    Images.getImage("bomb1"), Images.getImage("bomb2"), Images.getImage("bomb3")
  };
  private static BufferedImage[] expo = {
    Images.getImage("exp0"),
    Images.getImage("exp1"),
    Images.getImage("exp2"),
    Images.getImage("exp3"),
    Images.getImage("exp4"),
    Images.getImage("exp5"),
    Images.getImage("exp6"),
    Images.getImage("exp7"),
    Images.getImage("exp8"),
    Images.getImage("exp9"),
    Images.getImage("exp10"),
    Images.getImage("exp11"),
    Images.getImage("exp12"),
    Images.getImage("exp13"),
    Images.getImage("exp14"),
    Images.getImage("exp15"),
    Images.getImage("exp16")
  };
  private Handler handler;
  private int size = 75;
  private float speed = 0.5f;
  private boolean doExplode = false;
  private boolean detonated = false;
  private int animIndex = 0;
  private int animIndexExp = 0, counterExp = 0;
  private int playS = 1;
  private int counter;
  private int counter2;

  public Bomb(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
  }

  @Override
  public void tick() {
    counter2++;
    if (counter2 >= 6) {
      animIndex++;
      if (animIndex > 2) animIndex = 0;
      counter2 = 0;
    }
    counter++;
    if (counter >= 15) {
      counter = 0;
      speed += 1.5f;
    }
    if (doExplode) {
      velY = 0;
      velX = 0;
    }
    speed = Game.clamp(speed, 0f, 10f);
    float xx = (float) x;
    float yy = (float) y;
    float ww = Game.w / 2f;
    float hh = Game.h / 2f;
    float diffX = xx - ww;
    float diffY = yy - hh;
    float distance = (float) Math.sqrt(diffX * diffX + diffY * diffY);
    if (distance > size) {
      velX = (int) ((-speed / distance) * diffX);
      velY = (int) ((-speed / distance) * diffY);
    }
    if (distance <= size) {
      doExplode = true;
      if (!Menu.mute && playS > 0) {
        AudioPlayer.getSound("expo").play();
        playS--;
      }
      counterExp++;
      if (counterExp >= 5) {
        animIndexExp++;
        counterExp = 0;
      }
      if (!detonated) {
        handler.killAllEnemies();
        detonated = true;
      }
      if (animIndexExp >= expo.length) handler.removeIt(this);
    }
    x += velX;
    y += velY;
  }

  @Override
  public void render(Graphics g) {
    if (doExplode) {
      g.drawImage(expo[animIndexExp], (int) x - 93, (int) y - 90, null);
    } else {
      g.drawImage(anim[animIndex], (int) x, (int) y, null);
    }
  }

  @Override
  public Rectangle getBounds() {
    return null;
  }
}
