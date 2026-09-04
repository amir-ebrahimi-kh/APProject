/**
 * A standalone Java exercise component.
 */
package saving;

public class Stats {

  public int bullet; // 2
  public int game_level;
  public int score;
  public int bullet_level;
  public int bombs;
  public int lifes;
  public int coins;
  public int maxTemp;

  public Stats() {
    this.lifes = 5;
    this.bombs = 3;
    this.bullet_level = 1;
    this.game_level = 1;
    this.bullet = 0;
    this.maxTemp = 100;
  }

  public Stats(
      int game_level, int score, int bullet, int bullet_level, int bombs, int lifes, int coins) {

    this.game_level = game_level;
    this.score = score;
    this.bullet = bullet;
    this.bullet_level = bullet_level;
    this.bombs = bombs;
    this.lifes = lifes;
    this.coins = coins;
  }
}
