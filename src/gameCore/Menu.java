/**
 * A standalone Java exercise component.
 */
package gameCore;

import enemies.RectangleGroup;
import extras.AudioPlayer;
import inputs.InputManager;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import saving.SavingSystem;

public class Menu implements MouseListener {

  public static boolean mute = false;
  private Handler handler;
  private HUD hud;

  private Random r = new Random();
  private boolean menuMusicPlaying = false;
  private int plays = 1;
  private int playend = 1;
  private Game game;

  public Menu(Handler handler, HUD hud, Game game) {
    this.handler = handler;
    this.hud = hud;
    this.game = game;
  }

  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {
    int mx = e.getX();
    int my = e.getY();
    // main menu
    if (Game.gameState == STATE.Menu) {
      // new game
      if (mouseOver(mx, my, 550 + 100, 150 + 50, 250, 75)) {
        Game.gameState = STATE.Mode;
        return;
      }

      // continue
      if (mouseOver(mx, my, 550 + 100, 250 + 50, 250, 75)
          && Game.save.stats.game_level >= 2
          && Game.save.stats.game_level <= 20) {
        for (int i = 0; i < Spawn.levelsPassed.length; i++) {
          Spawn.levelsPassed[i] = false;
        }
        for (int i = 0; i < Game.save.stats.game_level - 2; i++) {
          Spawn.levelsPassed[i] = true;
        }
        Game.gameState = STATE.Game;
        handler.addObject(new Player(Game.w / 2 - 25, Game.h / 2 - 25, ID.Player, handler));
        hud.setLevel(Game.save.stats.game_level - 1);
        Spawn.progress = hud.getLevel() * 200;
        hud.setScore(Game.save.stats.score);
        HUD.coins = Game.save.stats.coins;
        HUD.HEALTH = Game.save.stats.lifes;
        HUD.bombs = Game.save.stats.bombs;
        InputManager.bulletPower = Game.save.stats.bullet_level;
        InputManager.type = Game.save.stats.bullet;
        HeatSystem.maxTemp = Game.save.stats.maxTemp;
      }
      // exit
      if (mouseOver(mx, my, 550 + 100, 550 + 50, 250, 75)) {
        System.exit(1);
      }
      // change player
      if (mouseOver(mx, my, 550 + 100, 450 + 50, 250, 75)) {
        game.stop();
        menuMusicPlaying = false;
        plays = 1;
        AudioPlayer.getMusic("menu_music").stop();
        Window.frame.remove(game);
        Window.frame.add(Window.panel);
        Window.frame.setVisible(true);
      }
      // about
      if (mouseOver(mx, my, Game.w - 275, Game.h - 100, 250, 75)) {
        Game.gameState = STATE.About;
      }
      // setting
      if (mouseOver(mx, my, 550 + 100, 350 + 50, 250, 75)) {
        Game.gameState = STATE.Setting;
      }
    }
    // select mode
    if (Game.gameState == STATE.Mode) {
      if (mouseOver(mx, my, 550 + 100, 150 + 50 + 100, 250, 75)) {
        Game.gameState = STATE.Game;
        HUD.coins = 0;
        handler.addObject(new Player(Game.w / 2 - 25, Game.h - 25, ID.Player, handler));
        HUD.bombs = 3;
        for (int i = 0; i < Spawn.levelsPassed.length; i++) {
          Spawn.levelsPassed[i] = false;
        }
        InputManager.bulletPower = 0;
        InputManager.type = 0;
        HeatSystem.maxTemp = 100;
        handler.addObject(
            new RectangleGroup(10, 100, 100, 5, ID.Level1RectangleGroup, handler, hud));
        return;
      }
      if (mouseOver(mx, my, 550 + 100, 150 + 50 + 200, 250, 75)) {
        Game.gameState = STATE.MPStart;
        return;
      }
      if (mouseOver(mx, my, 550 + 100, 150 + 50 + 300, 250, 75)) {
        Game.gameState = STATE.Menu;
        return;
      }
    }
    // in mp
    if (Game.gameState == STATE.MPStart) {
      if (mouseOver(mx, my, 550 + 100, 150 + 50 + 100, 250, 75)) {
        Game.gameState = STATE.ServerS;
        return;
      }
      if (mouseOver(mx, my, 550 + 100, 150 + 50 + 200, 250, 75)) {
        Game.gameState = STATE.ClientS;
        return;
      }
      if (mouseOver(mx, my, 550 + 100, 150 + 50 + 300, 250, 75)) {
        Game.gameState = STATE.Mode;
        return;
      }
    }
    // in server S
    if (Game.gameState == STATE.ServerS) {
      if (mouseOver(mx, my, 550 + 100, 150 + 50 + 100, 250, 75)) {}

      if (mouseOver(mx, my, 550 + 100, 150 + 50 + 200, 250, 75)) {}

      if (mouseOver(mx, my, 550 + 100, 150 + 50 + 300, 250, 75)) {
        Game.gameState = STATE.MPStart;
        return;
      }
    }
    // in client S
    if (Game.gameState == STATE.ClientS) {
      if (mouseOver(mx, my, 550 + 100, 150 + 50 + 100, 250, 75)) {}

      if (mouseOver(mx, my, 550 + 100, 150 + 50 + 200, 250, 75)) {}

      if (mouseOver(mx, my, 550 + 100, 150 + 50 + 300, 250, 75)) {
        Game.gameState = STATE.MPStart;
        return;
      }
    }
    // back for about
    if (Game.gameState == STATE.About) {
      if (mouseOver(mx, my, 550 + 100, 550 + 50, 250, 75)) {
        Game.gameState = STATE.Menu;
        return;
      }
    }
    // inside of setting
    if (Game.gameState == STATE.Setting) {
      if (mouseOver(mx, my, 690 + 100, 250 + 50, 250, 75)) {
        InputManager.ctrlWithMouse = true;
      }
      if (mouseOver(mx, my, 410 + 100, 250 + 50, 250, 75)) {
        InputManager.ctrlWithMouse = false;
      }
      if (mouseOver(mx, my, 790, 400, 250, 75)) {
        Player.color = false;
      }
      if (mouseOver(mx, my, 510, 400, 250, 75)) {
        Player.color = true;
      }
      if (mouseOver(mx, my, 790, 500, 250, 75)) {
        mute = true;
      }
      if (mouseOver(mx, my, 510, 500, 250, 75)) {
        mute = false;
      }
      if (mouseOver(mx, my, 550 + 100, 550 + 50, 250, 75)) {
        Game.gameState = STATE.Menu;
        return;
      }
    }
    // in game pause
    if (Game.gameState == STATE.Pause) {
      if (mouseOver(mx, my, 550 + 100, 250 + 50 + 50, 250, 75)) {
        Game.gameState = STATE.Game;
      }
      if (mouseOver(mx, my, 550 + 100, 350 + 50 + 50, 250, 75)) {
        Game.gameState = STATE.CQuit;
        return;
      }
    }
    if (Game.gameState == STATE.CQuit) {
      if (mouseOver(mx, my, 700 + 100, 350 + 50, 250, 75)) {
        handler.removeAll();
        Game.save.stats.bombs = HUD.bombs;
        Game.save.stats.maxTemp = HeatSystem.maxTemp;
        Game.save.stats.coins = HUD.coins;
        Game.save.stats.lifes = HUD.HEALTH;
        Game.save.stats.bullet = InputManager.type;
        Game.save.stats.bullet_level = InputManager.bulletPower;
        Game.save.stats.game_level = (((hud.getLevel() - 1) / 5) * 5) + 1;
        Game.save.stats.score = hud.getScore();
        hud.setLevel(1);
        hud.setScore(0);
        Spawn.progress = 0;
        HUD.HEALTH = 100;
        HeatSystem.temp = 0;
        HeatSystem.overHeated = false;
        InputManager.leftMouseDown = false;
        InputManager.spaceDown = false;
        Game.gameState = STATE.Menu;
        SavingSystem.save(Game.save);
      }
      if (mouseOver(mx, my, 400 + 100, 350 + 50, 250, 75)) {
        Game.gameState = STATE.Pause;
        return;
      }
    }
    // menu in end
    if (Game.gameState == STATE.End) {
      if (mouseOver(mx, my, 650, 470, 250, 75)) {
        handler.removeAll();
        Game.save.stats.bullet_level = 0;
        Game.save.stats.bullet = 0;
        Game.save.stats.maxTemp = 100;
        Game.save.stats.bombs = 3;
        Game.save.stats.coins = 0;
        Game.save.stats.lifes = 5;
        Game.save.stats.game_level = (((hud.getLevel() - 1) / 5) * 5) + 1;
        Game.save.stats.score = 0;
        HeatSystem.temp = 0;
        HeatSystem.overHeated = false;
        InputManager.leftMouseDown = false;
        InputManager.spaceDown = false;
        hud.setLevel(1);
        hud.setScore(0);
        Spawn.progress = 0;
        SavingSystem.save(Game.save);
        Game.gameState = STATE.Menu;
      }
    }
    if (Game.gameState == STATE.Won) {
      if (mouseOver(mx, my, 650, 470, 250, 75)) {
        handler.removeAll();
        Game.save.stats.bombs = 0;
        Game.save.stats.coins = 0;
        Game.save.stats.lifes = 0;
        Game.save.stats.game_level = hud.getLevel();
        Game.save.stats.score = hud.getScore();
        HeatSystem.temp = 0;
        HeatSystem.overHeated = false;
        InputManager.leftMouseDown = false;
        InputManager.spaceDown = false;
        hud.setLevel(1);
        hud.setScore(0);
        Spawn.progress = 0;
        SavingSystem.save(Game.save);
        Game.gameState = STATE.Menu;
      }
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
    if (mx > x && mx < x + width) {
      if (my > y && my < y + height) {
        return true;
      } else return false;
    } else return false;
  }

  public void tick() {
    if (Game.gameState != STATE.Game) {
      InputManager.leftMouseDown = false;
      InputManager.spaceDown = false;
    }
  }

  public void render(Graphics g) {

    if (Game.gameState == STATE.Menu) {

      g.setColor(Color.WHITE);
      g.setFont(new Font("arial", 1, 30));
      g.drawRect(550 + 100, 150 + 50, 250, 75);
      g.drawRect(550 + 100, 250 + 50, 250, 75);
      g.drawRect(550 + 100, 350 + 50, 250, 75);
      g.drawRect(550 + 100, 450 + 50, 250, 75);
      g.drawRect(550 + 100, 550 + 50, 250, 75);
      g.drawRect(Game.w - 275, Game.h - 100, 250, 75);
      g.drawString("Hello, " + Game.save.player + "!", 690, 170);
      g.setFont(new Font("arial", 1, 50));
      g.drawString("Space Wars", 630, 75);
      g.setFont(new Font("arial", 1, 30));
      g.drawString("New Game", 600 + 100, 195 + 50);
      g.drawString("Continue", 610 + 100, 295 + 50);
      g.drawString("Settings", 615 + 100, 395 + 50);
      g.drawString("Change Player", 630 + 40, 495 + 53);
      g.drawString("Hall of Fame", Game.w - 240, Game.h - 55);
      g.drawString("Exit", 645 + 100, 595 + 50);

    } else if (Game.gameState == STATE.About) {

      g.setColor(Color.RED);
      g.setFont(new Font("arial", 1, 50));
      g.drawString("Name", 300, 70);
      g.drawString("Level", 700, 70);
      g.drawString("Score", 1100, 70);
      g.setColor(Color.WHITE);
      g.setFont(new Font("arial", 1, 30));
      g.drawString("Back", 640 + 100, 595 + 50);
      g.drawRect(550 + 100, 550 + 50, 250, 75);
      for (int i = 0; i < SavingSystem.size(); i++) {
        if (i == 0) g.drawString(SavingSystem.getHallOfFame(i), 330, 120);
        else g.drawString(SavingSystem.getHallOfFame(i), 330, 120 + 40 * i);
      }

    } else if (Game.gameState == STATE.Pause) {

      g.setColor(Color.WHITE);
      g.setFont(new Font("arial", 1, 50));
      g.drawString("Paused", 690, 250);
      g.setFont(new Font("arial", 1, 30));
      g.drawString("Resume", 615 + 100, 295 + 50 + 50);
      g.drawRect(550 + 100, 250 + 50 + 50, 250, 75);
      g.drawString("Quit", 640 + 100, 395 + 50 + 50);
      g.drawRect(550 + 100, 350 + 50 + 50, 250, 75);

    } else if (Game.gameState == STATE.CQuit) {

      g.setColor(Color.WHITE);
      g.setFont(new Font("arial", 1, 50));
      g.drawString("Are You Sure?", 515 + 100, 200 + 50);
      g.setFont(new Font("arial", 1, 30));
      g.drawString("Yes", 800 + 100, 395 + 50);
      g.drawRect(700 + 100, 350 + 50, 250, 75);
      g.drawString("No", 505 + 100, 395 + 50);
      g.drawRect(400 + 100, 350 + 50, 250, 75);

    } else if (Game.gameState == STATE.Setting) {

      g.setColor(Color.WHITE);
      g.setFont(new Font("arial", 1, 50));
      g.drawString("Settings", 580 + 100, 200 + 50);
      g.setFont(new Font("arial", 1, 20));
      g.drawString("Play With Mouse", 740 + 100, 295 + 50);
      g.drawRect(690 + 100, 250 + 50, 250, 75);
      g.drawString("Play With Keyboard", 445 + 100, 295 + 50);
      g.drawRect(410 + 100, 250 + 50, 250, 75);
      g.drawString("Red SpaceShip", 845, 445);
      g.drawRect(790, 400, 250, 75);
      g.drawString("Blue SpaceShip", 560, 445);
      g.drawRect(510, 400, 250, 75);
      g.drawString("Mute Audio", 860, 545);
      g.drawRect(790, 500, 250, 75);
      g.drawString("Unmute Audio", 570, 545);
      g.drawRect(510, 500, 250, 75);
      g.setFont(new Font("arial", 1, 30));
      g.drawString("Back", 635 + 100, 597 + 50);
      g.drawRect(550 + 100, 550 + 50, 250, 75);

    } else if (Game.gameState == STATE.End) {

      g.setColor(Color.RED);
      g.setFont(new Font("arial", 1, 50));
      g.drawString("Game Over", 640, 300);
      g.setColor(Color.WHITE);
      g.setFont(new Font("arial", 1, 30));
      g.drawString("You lost with a score of", 560, 400);
      g.setColor(Color.RED);
      g.drawString(Integer.toString(hud.getScore()), 915, 400);
      g.setColor(Color.WHITE);
      g.drawRect(650, 470, 250, 75);
      g.drawString("Menu", 735, 515);

    } else if (Game.gameState == STATE.Won) {

      g.setColor(Color.YELLOW);
      g.setFont(new Font("arial", 1, 50));
      g.drawString("Congratulations", 580, 200);
      g.setColor(Color.WHITE);
      g.setFont(new Font("arial", 1, 30));
      g.drawString("You finished the game with a score of", 450, 300);
      g.drawString("Thank you for playing", 610, 400);
      g.setColor(Color.YELLOW);
      g.drawString(Integer.toString(hud.getScore()), 1010, 300);
      g.setColor(Color.WHITE);
      g.drawRect(650, 470, 250, 75);
      g.drawString("Menu", 735, 515);

    } else if (Game.gameState == STATE.Mode) {
      g.setColor(Color.WHITE);
      g.setFont(new Font("arial", 1, 50));
      g.drawString("Select mode", 550 + 80, 150);
      g.setFont(new Font("arial", 1, 25));
      g.drawString("SinglePlayer", 705, 345);
      g.drawString("MultiPlayer", 715, 445);
      g.drawString("Back", 745, 545);
      g.drawRect(550 + 100, 150 + 50 + 100, 250, 75);
      g.drawRect(550 + 100, 150 + 50 + 100 + 100, 250, 75);
      g.drawRect(550 + 100, 150 + 50 + 200 + 100, 250, 75);
    } else if (Game.gameState == STATE.MPStart) {
      g.setColor(Color.WHITE);
      g.setFont(new Font("arial", 1, 25));
      g.drawString("Host Game", 715, 345);
      g.drawString("Back", 745, 545);
      g.drawString("Connect to Game", 675, 445);
      g.drawRect(550 + 100, 150 + 50 + 100, 250, 75);
      g.drawRect(550 + 100, 150 + 50 + 200, 250, 75);
      g.drawRect(550 + 100, 150 + 50 + 300, 250, 75);
    } else if (Game.gameState == STATE.ServerS) {
      g.setColor(Color.WHITE);
      g.setFont(new Font("arial", 1, 25));
      g.drawString("Config", 735, 445);
      g.drawString("Back", 745, 545);
      g.drawString("Host", 745, 345);
      g.drawRect(550 + 100, 150 + 50 + 100, 250, 75);
      g.drawRect(550 + 100, 150 + 50 + 200, 250, 75);
      g.drawRect(550 + 100, 150 + 50 + 300, 250, 75);
    } else if (Game.gameState == STATE.ClientS) {
      g.setColor(Color.WHITE);
      g.setFont(new Font("arial", 1, 25));
      g.drawString("Config", 735, 445);
      g.drawString("Back", 745, 545);
      g.drawString("Connect", 730, 345);
      g.drawRect(550 + 100, 150 + 50 + 100, 250, 75);
      g.drawRect(550 + 100, 150 + 50 + 200, 250, 75);
      g.drawRect(550 + 100, 150 + 50 + 300, 250, 75);
    }
  }

  public void menuMusicTick() {
    if (Game.gameState == STATE.End && playend > 0 && !mute) {
      AudioPlayer.getMusic("game_over").loop();
      playend--;
    } else if (playend == 0 && Game.gameState != STATE.End) {
      AudioPlayer.getMusic("game_over").stop();
      playend++;
    }
    if (Game.gameState == STATE.Menu
        || Game.gameState == STATE.About
        || Game.gameState == STATE.Setting) {
      menuMusicPlaying = true;
    } else {
      menuMusicPlaying = false;
    }
    if (mute) {
      AudioPlayer.getMusic("menu_music").stop();
      plays = 1;
    }

    if (menuMusicPlaying && plays > 0 && !mute) {
      AudioPlayer.getMusic("menu_music").loop();
      plays--;
    }
    if (!menuMusicPlaying && plays == 0) {
      AudioPlayer.getMusic("menu_music").stop();
      plays++;
    }
    plays = Game.clamp(plays, 0, 1);
    playend = Game.clamp(playend, 0, 1);
  }
}
