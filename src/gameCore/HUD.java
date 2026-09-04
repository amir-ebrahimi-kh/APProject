/**
 * A standalone Java exercise component.
 */
package gameCore;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
  private int score = 0;
  private int level = 1;
  public static int HEALTH = 5;
  public static int bombs = 3;
  public static int coins = 0;

  public void tick() {
    HEALTH = Game.clamp(HEALTH, 0, 5);
  }

  public void render(Graphics g) {
    g.setColor(Color.GRAY);
    g.fillRect(25, 15, HeatSystem.maxTemp * 6, 50);
    int a = Game.clamp(HeatSystem.temp, 0, HeatSystem.maxTemp);
    g.setColor(new Color((a * 225) / HeatSystem.maxTemp, 0, 0));
    g.fillRect(25, 15, HeatSystem.temp * 6, 50);
    g.setColor(Color.WHITE);
    g.drawRect(25, 15, HeatSystem.maxTemp * 6, 50);

    //		g.setColor(Color.GRAY);
    //		g.fillRect(Game.w - 225, 15, 200, 20);
    //		g.setColor(Color.getHSBColor((1f * HEALTH) / 360, 1f, 1f));
    //		g.fillRect(Game.w - 225, 15, HEALTH * 2, 20);
    //		g.setColor(Color.WHITE);
    //		g.drawRect(Game.w - 225, 15, 200, 20);

    g.drawString("Score: " + score, Game.w - 225, 50);
    g.drawString("Level: " + level, Game.w - 225, 65);
    g.drawString("Bombs: " + bombs, 15, Game.h - 40);
    g.drawString("Coins: " + coins, 15, Game.h - 20);
    g.drawString("Lifes: " + HEALTH, 15, Game.h - 60);

    if (HeatSystem.overHeated) {
      g.setFont(new Font("arial", 1, 30));
      g.setColor(Color.RED);
      g.drawString("OverHeat!", (HeatSystem.maxTemp * 6) + 50, 49);
    }
  }

  public int getScore() {
    return score;
  }

  public void addedScore(int score) {
    Spawn.progress += score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }
}
