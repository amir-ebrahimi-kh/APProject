/**
 * A standalone Java exercise component.
 */
package saving;

public class Save {

  public String player;
  public Stats stats;

  public Save(String player, Stats stats) {

    this.player = player;
    this.stats = stats;
  }

  public Save(String player) {
    this.player = player;
    this.stats = new Stats();
  }
}
