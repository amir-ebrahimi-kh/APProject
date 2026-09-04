/**
 * A standalone Java exercise component.
 */
package inputs;

import bullets.*;
import extras.AudioPlayer;
import gameCore.*;
import java.awt.event.*;
import javax.swing.*;

public class InputManager implements MouseMotionListener, MouseListener, KeyListener {

  public static boolean playerIsAlive = true;
  public static boolean spaceDown = false;
  public static boolean ctrlWithMouse = false;
  public static boolean leftMouseDown = false;
  private boolean firstBul = true;
  private Handler handler;
  public static int bulletPower;
  public static int type;
  private static ID id;
  private boolean[] keyDown = new boolean[4];
  private Timer t =
      new Timer(
          200,
          new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              if ((leftMouseDown || spaceDown) && !HeatSystem.overHeated) {
                shootBullet(id);
              }
            }
          });

  public InputManager(Handler handler) {
    this.handler = handler;
    keyDown[0] = false;
    keyDown[1] = false;
    keyDown[2] = false;
    keyDown[3] = false;
  }

  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {
    sync();
    t.setDelay(Bullet.getDelay(id));
    int x = e.getButton();
    if (ctrlWithMouse && playerIsAlive) {
      if (Game.gameState == STATE.Game && x == MouseEvent.BUTTON1) {
        if (firstBul && !HeatSystem.overHeated) {
          shootBullet(id);
          firstBul = false;
        }
        leftMouseDown = true;
        t.start();
      }
      if (Game.gameState == STATE.Game && x == MouseEvent.BUTTON3 && HUD.bombs > 0) {
        shootBomb();
      }
    }
  }

  private void sync() {
    switch (type) {
      case 0:
        id = ID.Bullet1;
        break;
      case 1:
        id = ID.Bullet2;
        break;
      case 2:
        id = ID.Bullet3;
        break;
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    int x = e.getButton();
    if (ctrlWithMouse && playerIsAlive) {
      if (Game.gameState == STATE.Game && x == MouseEvent.BUTTON1) {
        firstBul = true;
        leftMouseDown = false;
        t.stop();
      }
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mouseDragged(MouseEvent e) {
    if (Game.gameState == STATE.Game) {
      Player.tX = e.getX();
      Player.tY = e.getY();
    }
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (Game.gameState == STATE.Game) {
      Player.tX = e.getX();
      Player.tY = e.getY();
    }
  }

  public void keyPressed(KeyEvent e) {
    sync();
    t.setDelay(Bullet.getDelay(id));
    int key = e.getKeyCode();
    if (!InputManager.ctrlWithMouse && Game.gameState == STATE.Game) {
      for (int i = 0; i < handler.size(); i++) {
        GameObject temp = handler.getObjectIn(i);
        if (temp.getId() == ID.Player) {
          if (key == KeyEvent.VK_UP) {
            temp.setVelY(-6);
            keyDown[0] = true;
          }
          if (key == KeyEvent.VK_DOWN) {
            temp.setVelY(6);
            keyDown[1] = true;
          }
          if (key == KeyEvent.VK_RIGHT) {
            temp.setVelX(6);
            keyDown[2] = true;
          }
          if (key == KeyEvent.VK_LEFT) {
            temp.setVelX(-6);
            keyDown[3] = true;
          }
        }
      }
    }
    if (key == KeyEvent.VK_ESCAPE) {
      if (Game.gameState == STATE.Game) {
        Game.gameState = STATE.Pause;
      }
    }
    if (key == KeyEvent.VK_SPACE
        && Game.gameState == STATE.Game
        && !InputManager.ctrlWithMouse
        && playerIsAlive) {
      if (firstBul && !HeatSystem.overHeated) {
        shootBullet(id);
        firstBul = false;
      }
      spaceDown = true;
      t.start();
    }
    if (key == KeyEvent.VK_ENTER && Game.gameState == STATE.Game && playerIsAlive) {
      if (!InputManager.ctrlWithMouse && HUD.bombs > 0) {
        shootBomb();
      }
    }
  }

  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    if (!InputManager.ctrlWithMouse && Game.gameState == STATE.Game) {
      if (key == KeyEvent.VK_SPACE) firstBul = true;
      if (key == KeyEvent.VK_UP) keyDown[0] = false;
      if (key == KeyEvent.VK_DOWN) keyDown[1] = false;
      if (key == KeyEvent.VK_RIGHT) keyDown[2] = false;
      if (key == KeyEvent.VK_LEFT) keyDown[3] = false;

      for (int i = 0; i < handler.size(); i++) {
        GameObject temp = handler.getObjectIn(i);
        if (temp.getId() == ID.Player) {
          if (!keyDown[0] && !keyDown[1]) {
            temp.setVelY(0);
          }
          if (!keyDown[2] && !keyDown[3]) {
            temp.setVelX(0);
          }
        }
      }
      if (key == KeyEvent.VK_SPACE) {
        spaceDown = false;
        t.stop();
      }
    }
  }

  public void keyTyped(KeyEvent e) {}

  public void shootBullet(ID bullet) {
    if (bullet == ID.Bullet1) {
      if (bulletPower == 0) {
        handler.addObject(new Bullet1(Player.X + 72 / 2 - 2, Player.Y, ID.Bullet, handler, 0));
      }
      if (bulletPower == 1) {
        handler.addObject(new Bullet1(Player.X + (72 / 2) + 10, Player.Y, ID.Bullet, handler, 0));
        handler.addObject(new Bullet1(Player.X + (72 / 2) - 14, Player.Y, ID.Bullet, handler, 0));
      }
      if (bulletPower == 2) {
        handler.addObject(new Bullet1(Player.X + (72 / 2) + 6, Player.Y, ID.Bullet, handler, 1));
        handler.addObject(new Bullet1(Player.X + (72 / 2) - 10, Player.Y, ID.Bullet, handler, -1));
        handler.addObject(new Bullet1(Player.X + (72 / 2) - 2, Player.Y, ID.Bullet, handler, 0));
      }
      if (bulletPower >= 3) {
        handler.addObject(new Bullet1(Player.X + (72 / 2) + 8, Player.Y, ID.Bullet, handler, 0));
        handler.addObject(new Bullet1(Player.X + (72 / 2) - 12, Player.Y, ID.Bullet, handler, 0));
        handler.addObject(
            new Bullet1(Player.X + (72 / 2) + 10, Player.Y, ID.Bullet, handler, +0.5));
        handler.addObject(
            new Bullet1(Player.X + (72 / 2) - 14, Player.Y, ID.Bullet, handler, -0.5));
      }
      if (!Menu.mute) AudioPlayer.getSound("bullet1").play();
      HeatSystem.temp += Bullet.getTemp(id);
    }
    if (bullet == ID.Bullet2) {
      if (bulletPower == 0) {
        handler.addObject(new Bullet2(Player.X + 72 / 2 - 2, Player.Y, ID.Bullet, handler, 0));
      }
      if (bulletPower == 1) {
        handler.addObject(new Bullet2(Player.X + (72 / 2) + 10, Player.Y, ID.Bullet, handler, 0));
        handler.addObject(new Bullet2(Player.X + (72 / 2) - 14, Player.Y, ID.Bullet, handler, 0));
      }
      if (bulletPower == 2) {
        handler.addObject(new Bullet2(Player.X + (72 / 2) + 6, Player.Y, ID.Bullet, handler, 1));
        handler.addObject(new Bullet2(Player.X + (72 / 2) - 10, Player.Y, ID.Bullet, handler, -1));
        handler.addObject(new Bullet2(Player.X + (72 / 2) - 2, Player.Y, ID.Bullet, handler, 0));
      }
      if (bulletPower >= 3) {
        handler.addObject(new Bullet2(Player.X + (72 / 2) + 8, Player.Y, ID.Bullet, handler, 0));
        handler.addObject(new Bullet2(Player.X + (72 / 2) - 12, Player.Y, ID.Bullet, handler, 0));
        handler.addObject(
            new Bullet2(Player.X + (72 / 2) + 10, Player.Y, ID.Bullet, handler, +0.5));
        handler.addObject(
            new Bullet2(Player.X + (72 / 2) - 14, Player.Y, ID.Bullet, handler, -0.5));
      }
      if (!Menu.mute) AudioPlayer.getSound("bullet2").play();
      HeatSystem.temp += Bullet.getTemp(id);
    }
    if (bullet == ID.Bullet3) {
      if (bulletPower == 0) {
        handler.addObject(new Bullet3(Player.X + 72 / 2 - 2, Player.Y, ID.Bullet, handler, 0));
      }
      if (bulletPower == 1) {
        handler.addObject(new Bullet3(Player.X + (72 / 2) + 10, Player.Y, ID.Bullet, handler, 0));
        handler.addObject(new Bullet3(Player.X + (72 / 2) - 14, Player.Y, ID.Bullet, handler, 0));
      }
      if (bulletPower == 2) {
        handler.addObject(new Bullet3(Player.X + (72 / 2) + 6, Player.Y, ID.Bullet, handler, 1));
        handler.addObject(new Bullet3(Player.X + (72 / 2) - 10, Player.Y, ID.Bullet, handler, -1));
        handler.addObject(new Bullet3(Player.X + (72 / 2) - 2, Player.Y, ID.Bullet, handler, 0));
      }
      if (bulletPower >= 3) {
        handler.addObject(new Bullet3(Player.X + (72 / 2) + 8, Player.Y, ID.Bullet, handler, 0));
        handler.addObject(new Bullet3(Player.X + (72 / 2) - 12, Player.Y, ID.Bullet, handler, 0));
        handler.addObject(
            new Bullet3(Player.X + (72 / 2) + 10, Player.Y, ID.Bullet, handler, +0.5));
        handler.addObject(
            new Bullet3(Player.X + (72 / 2) - 14, Player.Y, ID.Bullet, handler, -0.5));
      }
      if (!Menu.mute) AudioPlayer.getSound("bullet3").play();
      HeatSystem.temp += Bullet.getTemp(id);
    }
  }

  public void shootBomb() {
    handler.addObject(new Bomb(Player.X, Player.Y, ID.Bomb, handler));
    HUD.bombs--;
  }
}
