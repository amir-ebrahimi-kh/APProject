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

public class MultiMovingSpinningGroup extends GameObject {
  private int[] sizes = new int[4];
  private ID id1;
  private ID id2;
  private ID id3;
  private ID id4;

  private ArrayList<SingleMovingSpinningGroup> ss = new ArrayList<>();
  private boolean moving = false;
  private Handler handler;
  private HUD hud;
  private double speed;
  private int counter;

  public MultiMovingSpinningGroup(
      double x,
      double y,
      ID id,
      ID id1,
      int size1,
      ID id2,
      int size2,
      ID id3,
      int size3,
      ID id4,
      int size4,
      double speed,
      Handler handler,
      HUD hud) {
    super(x, y, id);
    this.handler = handler;
    this.hud = hud;
    this.speed = speed;
    sizes[0] = size1;
    sizes[1] = size2;
    sizes[2] = size3;
    sizes[3] = size4;
    this.id1 = id1;
    this.id2 = id2;
    this.id3 = id3;
    this.id4 = id4;
    load();
  }

  private void load() {
    if (sizes[0] != 0)
      ss.add(new SingleMovingSpinningGroup(x, y, id1, sizes[0], 150, 0.5, speed, handler, hud));
    if (sizes[1] != 0)
      ss.add(new SingleMovingSpinningGroup(x, y, id2, sizes[1], 250, 0.5, speed, handler, hud));
    if (sizes[2] != 0)
      ss.add(new SingleMovingSpinningGroup(x, y, id3, sizes[2], 350, 0.5, speed, handler, hud));
    if (sizes[3] != 0)
      ss.add(new SingleMovingSpinningGroup(x, y, id4, sizes[3], 450, 0.5, speed, handler, hud));
    for (int i = 1; i < 4; i++) {
      ss.get(i).isLeader = false;
    }
    for (int i = 0; i < 4; i++) {
      handler.addObject(ss.get(i));
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
    double X;
    double Y;
    X = ss.get(0).getX();
    Y = ss.get(0).getY();
    for (int i = 1; i < 4; i++) {
      ss.get(i).setX(X);
      ss.get(i).setY(Y);
    }
  }

  @Override
  public void render(Graphics g) {}

  @Override
  public Rectangle getBounds() {
    return null;
  }
}
