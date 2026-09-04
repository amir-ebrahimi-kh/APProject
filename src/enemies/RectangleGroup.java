/**
 * A standalone Java exercise component.
 */
package enemies;

import gameCore.*;
import java.awt.*;
import java.util.ArrayList;

public class RectangleGroup extends GameObject {

  private ArrayList<GameObject> arrayList;
  private int size;
  private boolean moving = false;
  private int width;
  private Handler handler;
  private HUD hud;
  private int space = 120;
  private int counter;

  public RectangleGroup(int size, int x, int y, int velX, ID id, Handler handler, HUD hud) {
    super(x, y, id);
    this.size = size;
    this.velX = velX;
    this.handler = handler;
    this.hud = hud;
    if (size > 40) {
      width = 9 * space;
    } else if (size >= 30) {
      width = 8 * space;
    } else {
      width = 7 * space;
    }
    arrayList = new ArrayList<>();
    load();
  }

  private void load() {
    int n = 0;
    int m = 0;
    switch (id) {
      case Level1RectangleGroup:
        for (int i = 0; i < size; i++) {
          m = i;
          if (((m * space) % width == 0) && m != 0) {
            n += space;
          }
          arrayList.add(
              new Level1Enemy(
                  x + ((i * space) % (width)), y + n, ID.Enemy, handler, (int) velX, 0, hud));
        }
        break;
      case Level2RectangleGroup:
        for (int i = 0; i < size; i++) {
          m = i;
          if (((m * space) % width == 0) && m != 0) {
            n += space;
          }
          arrayList.add(
              new Level2Enemy(
                  x + ((i * space) % (width)), y + n, ID.Enemy, handler, (int) velX, 0, hud));
        }
        break;
      case Level3RectangleGroup:
        for (int i = 0; i < size; i++) {
          m = i;
          if (((m * space) % width == 0) && m != 0) {
            n += space;
          }
          arrayList.add(
              new Level3Enemy(
                  x + ((i * space) % (width)), y + n, ID.Enemy, handler, (int) velX, 0, hud));
        }
        break;
      case Level4RectangleGroup:
        for (int i = 0; i < size; i++) {
          m = i;
          if (((m * space) % width == 0) && m != 0) {
            n += space;
          }
          arrayList.add(
              new Level4Enemy(
                  x + ((i * space) % (width)), y + n, ID.Enemy, handler, (int) velX, 0, hud));
        }
        break;
    }
    loadToHandler();
  }

  private void loadToHandler() {
    for (int i = 0; i < size; i++) {
      handler.addObject(arrayList.get(i));
    }
  }

  @Override
  public void tick() {
    if (!moving) {
      counter++;
      if (counter >= 300) {
        counter = 0;
        moving = true;
      }
    }
    int xs = 0;
    for (GameObject enemy : arrayList) {
      if (handler.isIn(enemy)) xs++;
    }
    if (xs == 0) {
      handler.removeIt(this);
    }
    if (moving) {
      x += velX;
      if (x + width >= Game.w) {
        velX *= -1d;
      }
      if (x <= 0) {
        velX *= -1d;
      }
      for (int i = 0; i < size; i++) {
        arrayList.get(i).setVelX(velX);
      }
    }
  }

  @Override
  public void render(Graphics g) {}

  @Override
  public Rectangle getBounds() {
    return null;
  }
}
