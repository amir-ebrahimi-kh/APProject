/**
 * A standalone Java exercise component.
 */
package enemies;

import gameCore.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TerroristGroup extends GameObject {

  private ArrayList<Enemy> arrayList;
  private int size;
  private Handler handler;
  private HUD hud;
  private double lastXofP, lastYofP;
  private Random r = new Random();
  private int counter;

  public TerroristGroup(double x, double y, ID id, int size, Handler handler, HUD hud) {
    super(x, y, id);
    this.size = size;
    this.handler = handler;
    this.hud = hud;
    arrayList = new ArrayList<>();
    load();
  }

  private void load() {
    switch (id) {
      case Level1TerroristGroup:
        for (int i = 0; i < size; i++) {
          arrayList.add(
              new Level1Enemy(r.nextInt(Game.w), r.nextInt(Game.h), ID.Enemy, handler, 0, 0, hud));
        }
        break;
      case Level2TerroristGroup:
        for (int i = 0; i < size; i++) {
          arrayList.add(
              new Level2Enemy(r.nextInt(Game.w), r.nextInt(Game.h), ID.Enemy, handler, 0, 0, hud));
        }
        break;
      case Level3TerroristGroup:
        for (int i = 0; i < size; i++) {
          arrayList.add(
              new Level3Enemy(r.nextInt(Game.w), r.nextInt(Game.h), ID.Enemy, handler, 0, 0, hud));
        }
        break;
      case Level4TerroristGroup:
        for (int i = 0; i < size; i++) {
          arrayList.add(
              new Level4Enemy(r.nextInt(Game.w), r.nextInt(Game.h), ID.Enemy, handler, 0, 0, hud));
        }
        break;
    }
    for (int i = 0; i < arrayList.size(); i++) {
      handler.addObject(arrayList.get(i));
      arrayList.get(i).setTg(this);
    }
  }

  @Override
  public void tick() {
    int xs = 0;
    counter++;
    if (counter >= 600) {
      counter = 0;
      for (Enemy enemy : arrayList) {
        enemy.isLeader = false;
      }
      lastXofP = Player.X;
      lastYofP = Player.Y;
      arrayList.get(r.nextInt(arrayList.size())).isLeader = true;
    }
    for (Enemy enemy : arrayList) {
      if (enemy.isAlive) xs++;
    }
    if (xs == 0) {
      handler.removeIt(this);
    }
    for (Enemy enemy : arrayList) {
      if (enemy.reachedDest && enemy.reachedFirstDest) {
        enemy.reachedDest = false;
        enemy.targetX = r.nextInt(Game.w);
        enemy.targetY = r.nextInt(Game.h);
      }
      if (enemy.reachedFirstDest && !enemy.reachedDest && !enemy.isLeader) {
        enemy.goTo(enemy.targetX, enemy.targetY, 3d);
      }
      if (enemy.reachedFirstDest && !enemy.reachedDest && enemy.isLeader)
        enemy.attackPlayer(lastXofP, lastYofP, 9d);
    }
  }

  @Override
  public void render(Graphics g) {}

  public void remove(Enemy enemy) {
    arrayList.remove(enemy);
  }

  @Override
  public Rectangle getBounds() {
    return null;
  }
}
