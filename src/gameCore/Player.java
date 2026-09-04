/**
 * A standalone Java exercise component.
 */
package gameCore;

import extras.Images;
import inputs.InputManager;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject {

  public static int X;
  public static int Y;
  public static int tX = Game.w / 2;
  public static int tY = Game.h / 2;
  public static boolean hasShield = false;
  public static boolean color = true; // true == blue false == red
  private int size = 72;
  private Handler handler;
  private BufferedImage[] animred = {
    Images.getImage("rplayer1"),
    Images.getImage("rplayer2"),
    Images.getImage("rplayer3"),
    Images.getImage("rplayer4"),
    Images.getImage("rplayer5")
  };
  private BufferedImage[] animblue = {
    Images.getImage("bplayer1"),
    Images.getImage("bplayer2"),
    Images.getImage("bplayer3"),
    Images.getImage("bplayer4"),
    Images.getImage("bplayer5")
  };

  private int animIndex = 0;
  private int counter = 0;

  public Player(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    InputManager.playerIsAlive = true;
    this.handler = handler;
    hasShield = true;
  }

  public void tick() {
    counter++;
    if (counter >= 10) {
      animIndex++;
      if (animIndex > 4) {
        animIndex = 0;
      }
      counter = 0;
    }

    if (InputManager.ctrlWithMouse) {
      x = tX - size / 2;
      y = tY - size / 2;
    } else {
      x += velX;
      y += velY;
    }
    x = Game.clamp((int) x, 0, Game.w - size);
    y = Game.clamp((int) y, 0, Game.h - size);
    X = (int) x;
    Y = (int) y;
  }

  public void render(Graphics g) {
    g.setColor(Color.RED);
    if (hasShield) g.drawString("Shield", (int) x, (int) y);

    if (color) g.drawImage(animblue[animIndex], (int) x - 12, (int) y - 4, null);
    else g.drawImage(animred[animIndex], (int) x - 12, (int) y - 4, null);
  }

  public Rectangle getBounds() {
    return new Rectangle((int) x, (int) y, size, size);
  }
}
