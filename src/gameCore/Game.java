/**
 * A standalone Java exercise component.
 */
package gameCore;

import extras.AudioPlayer;
import extras.Images;
import inputs.InputManager;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import saving.Save;

public class Game extends Canvas implements Runnable {

  public static final int w = 1536, h = 864;

  /** */
  private static final long serialVersionUID = 1L;

  public static STATE gameState = STATE.Menu;
  public static Save save;
  private Thread thread;
  private boolean running = false;
  private Handler handler;
  private HUD hud;
  private Spawn spawn;
  private Menu menu;
  private int x = 0;
  private BufferedImage b;
  private BufferedImage b1;
  private int xx = 0;
  private Cursor blankCursor;

  public Game() {

    hud = new HUD();
    handler = new Handler();
    menu = new Menu(handler, hud, this);
    addKeyListener(new InputManager(handler));
    addMouseListener(new InputManager(handler));
    addMouseMotionListener(new InputManager(handler));

    AudioPlayer.load();
    Images.load();
    b = Images.getImage("back_ground");
    b1 = Images.getImage("back_ground2");

    new Window(w, h, "My First Game", this);

    spawn = new Spawn(handler, hud);
    addMouseListener(menu);
  }

    /**
     * Main entry point for the exercise.
     */
  public static void main(String[] args) {
    new Game();
  }

  public static int clamp(int var, int min, int max) {
    if (var >= max) {
      return var = max;
    } else if (var <= min) {
      return var = min;
    }
    return var;
  }

  public static float clamp(float var, float min, float max) {
    if (var >= max) {
      return var = max;
    } else if (var <= min) {
      return var = min;
    }
    return var;
  }

  public static double clamp(double var, double min, double max) {
    if (var >= max) {
      return var = max;
    } else if (var <= min) {
      return var = min;
    }
    return var;
  }

  public void run() {
    this.requestFocus();
    long lastTime = System.nanoTime();
    final double amountOfTicks = 60.0;
    final double ns = 1000000000.0 / amountOfTicks;
    double delta = 0.0;
    long timer = System.currentTimeMillis();
    int updates = 0;
    while (running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1.0) {
        tick();
        updates++;
        delta--;
      }
      render();
      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        //                System.out.println("FPS: " + updates);
        updates = 0;
      }
    }
    stop();
  }

  private void render() {

    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, w, h);
    Cursor c = null;
    if (xx == 0) {
      c = this.getCursor();
      BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
      blankCursor =
          Toolkit.getDefaultToolkit()
              .createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
      xx++;
    }
    if (gameState == STATE.Game) {
      this.setCursor(blankCursor);
      g.drawImage(b, 0, x - 864, null);
      g.drawImage(b1, 0, x, null);
      handler.render(g);
      hud.render(g);
    } else if (gameState == STATE.Menu
        || gameState == STATE.About
        || gameState == STATE.Setting
        || gameState == STATE.End
        || gameState == STATE.Won
        || gameState == STATE.Mode
        || gameState == STATE.ClientS
        || gameState == STATE.MPStart
        || gameState == STATE.ServerS) {
      menu.render(g);
      this.setCursor(c);
    } else if (gameState == STATE.Pause || gameState == STATE.CQuit) {
      g.drawImage(b, 0, x - 864, null);
      g.drawImage(b1, 0, x, null);
      handler.render(g);
      hud.render(g);
      menu.render(g);
      this.setCursor(c);
    }
    g.dispose();
    bs.show();
  }

  private void tick() {
    menu.menuMusicTick();
    if (gameState == STATE.Game) {
      x += 1;
      if (x > h) {
        x = 0;
      }
      handler.tick();
      hud.tick();
      spawn.tick();
      if (HUD.HEALTH <= 0) {
        HUD.HEALTH = 5;
        handler.removeAll();
        gameState = STATE.End;
      }
    } else if (gameState == STATE.Menu
        || gameState == STATE.About
        || gameState == STATE.Setting
        || gameState == STATE.Pause
        || gameState == STATE.End
        || gameState == STATE.CQuit
        || gameState == STATE.ClientS
        || gameState == STATE.Won
        || gameState == STATE.Mode
        || gameState == STATE.MPStart
        || gameState == STATE.ServerS) {
      menu.tick();
    }
  }

  public synchronized void start() {
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  public synchronized void stop() {
    try {
      thread.stop();
      running = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
