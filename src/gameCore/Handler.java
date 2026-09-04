/**
 * A standalone Java exercise component.
 */
package gameCore;

import inputs.InputManager;
import java.awt.*;
import java.util.LinkedList;

public class Handler {

  private LinkedList<GameObject> objects = new LinkedList<GameObject>();
  private int counter, counter2;

  public void tick() {
    for (int i = 0; i < objects.size(); i++) {
      GameObject temp = objects.get(i);
      temp.tick();
    }
    if (!InputManager.playerIsAlive) counter++;
    if (counter >= 300) {
      counter = 0;
      doo();
      //            Player.hasShield = true;
    }
    if (Player.hasShield) counter2++;
    if (counter2 >= 300) {
      counter2 = 0;
      Player.hasShield = false;
    }
    HeatSystem.sync();
  }

  public void render(Graphics g) {
    for (int i = 0; i < objects.size(); i++) {
      GameObject temp = objects.get(i);
      temp.render(g);
    }
  }

  public void addObject(GameObject gameobj) {
    objects.add(gameobj);
  }

  public void removeObject(GameObject gameobj) {
    objects.remove(gameobj);
  }

  public GameObject getObjectIn(int i) {
    return objects.get(i);
  }

  public int size() {
    return objects.size();
  }

  public void removeIt(GameObject a) {
    objects.remove(a);
  }

  public void removeIt(int i) {
    objects.remove(i);
  }

  public void removeAll() {
    objects.clear();
  }

  public void removeEnemies() {
    for (GameObject gameObject : objects) {
      if (gameObject.id != ID.Player) {
        objects.remove(gameObject);
      }
    }
  }

  public void killAllEnemies() {
    for (GameObject gameObject : objects) {
      if (gameObject.id == ID.Enemy || gameObject.id == ID.Coin) {
        gameObject.health = 0;
      }
      if (gameObject.id == ID.Boss) gameObject.health -= 50;
    }
  }

  public void respawnPlayer() {
    if (HUD.HEALTH >= 1) {
      HUD.coins = 0;
      InputManager.bulletPower = 0;
      HeatSystem.maxTemp = 100;
      InputManager.playerIsAlive = false;
    }
  }

  private void doo() {
    this.addObject(new Player(Game.w / 2 - 25, Game.h / 2 + 400, ID.Player, this));
  }

  public boolean isIn(GameObject gameObject) {
    return objects.contains(gameObject);
  }

  public void clean() {
    for (int i = 0; i < objects.size(); i++) {
      GameObject temp = objects.get(i);
      if (temp.id == ID.MMSG
          || temp.id == ID.Level1RectangleGroup
          || temp.id == ID.Level2RectangleGroup
          || temp.id == ID.Level3RectangleGroup
          || temp.id == ID.Level4RectangleGroup) objects.remove(temp);
    }
  }
}
