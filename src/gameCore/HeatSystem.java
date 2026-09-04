/**
 * A standalone Java exercise component.
 */
package gameCore;


import inputs.InputManager;
import javax.swing.Timer;

public class HeatSystem {

  public static int temp = 0;
  public static boolean overHeated = false;
  public static boolean cooling = false;
  public static int maxTemp = 100;

  private static Timer t =
      new Timer(
          40,
          e -> {
            if (overHeated && Game.gameState == STATE.Game) {
              temp -= 1;
            }
          });

  private static Timer t1 =
      new Timer(
          40,
          e -> {
            if (cooling && Game.gameState == STATE.Game) {
              temp -= 1;
            }
          });

  public static void sync() {
    if (temp >= maxTemp) {
      overHeated = true;
      t.start();
    }
    if (temp <= 0) {
      overHeated = false;
      t.stop();
    }
    if (InputManager.ctrlWithMouse) {
      if (!InputManager.leftMouseDown && !overHeated) {
        cooling = true;
        t1.start();
      } else if (InputManager.leftMouseDown || overHeated) {
        cooling = false;
        t1.stop();
      }
    }
    if (!InputManager.ctrlWithMouse) {
      if (!InputManager.spaceDown && !overHeated) {
        cooling = true;
        t1.start();
      } else if (InputManager.spaceDown || overHeated) {
        cooling = false;
        t1.stop();
      }
    }
    temp = Game.clamp(temp, 0, maxTemp + 1);
  }
}
