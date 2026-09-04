/**
 * A standalone Java exercise component.
 */
package enemies;

import gameCore.GameObject;
import gameCore.HUD;
import gameCore.Handler;
import gameCore.ID;
import java.awt.*;
import java.util.ArrayList;

public class SpinningGroup extends GameObject {

  protected boolean moving = false;
  private ArrayList<Enemy> arrayList;
  private int size;
  private double radius;
  private Handler handler;
  private HUD hud;
  private double angularSpeed;
  private int counter;

  public SpinningGroup(
      double x,
      double y,
      ID id,
      int size,
      double radius,
      double angularSpeed,
      Handler handler,
      HUD hud) {
    super(x, y, id);
    this.size = size;
    this.angularSpeed = angularSpeed * Math.PI / 180d;
    this.radius = radius;
    this.handler = handler;
    this.hud = hud;
    arrayList = new ArrayList<>();
    load();
  }

  private void load() {
    switch (id) {
      case Level1SpinningGroup:
        for (int i = 0; i < 360; i += (360 / size)) {
          arrayList.add(
              new Level1Enemy(
                  x + radius * Math.cos(i * Math.PI / 180),
                  y + radius * Math.sin(i * Math.PI / 180),
                  ID.Enemy,
                  handler,
                  0,
                  0,
                  hud));
        }

        break;
      case Level2SpinningGroup:
        for (int i = 0; i < 360; i += (360 / size)) {
          arrayList.add(
              new Level2Enemy(
                  x + radius * Math.cos(i * Math.PI / 180),
                  y + radius * Math.sin(i * Math.PI / 180),
                  ID.Enemy,
                  handler,
                  0,
                  0,
                  hud));
        }
        break;
      case Level3SpinningGroup:
        for (int i = 0; i < 360; i += (360 / size)) {
          arrayList.add(
              new Level3Enemy(
                  x + radius * Math.cos(i * Math.PI / 180),
                  y + radius * Math.sin(i * Math.PI / 180),
                  ID.Enemy,
                  handler,
                  0,
                  0,
                  hud));
        }
        break;
      case Level4SpinningGroup:
        for (int i = 0; i < 360; i += (360 / size)) {
          arrayList.add(
              new Level4Enemy(
                  x + radius * Math.cos(i * Math.PI / 180),
                  y + radius * Math.sin(i * Math.PI / 180),
                  ID.Enemy,
                  handler,
                  0,
                  0,
                  hud));
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
    if (!moving) {
      for (Enemy enemy : arrayList) {
        double myy = enemy.getY() - y;
        double myx = (enemy.getX() - x);
        double f = myy / myx;
        if (myx > 0) enemy.theta = Math.atan(f);
        else if (myx < 0 && myy >= 0) enemy.theta = Math.atan(f) + Math.PI;
        else if (myx < 0 && myy < 0) enemy.theta = Math.atan(f) - Math.PI;
        else if (myx == 0 && myy > 0) enemy.theta = Math.PI / 2;
        else if (myx == 0 && myy < 0) enemy.theta = -Math.PI / 2;
        else if (myx == 0 && myy == 0) enemy.theta = 0;
      }
    }
    if (moving) {
      x += velX;
      y += velY;
      for (Enemy enemy : arrayList) {
        enemy.theta += angularSpeed;
        enemy.setX((radius * Math.cos(enemy.theta)) + x);
        enemy.setY((radius * Math.sin(enemy.theta)) + y);
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
