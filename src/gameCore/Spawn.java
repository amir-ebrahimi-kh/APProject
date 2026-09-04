/**
 * A standalone Java exercise component.
 */
package gameCore;

import enemies.*;
import extras.AudioPlayer;
import java.security.SecureRandom;

public class Spawn {
  public static int progress;
  public static boolean[] levelsPassed = new boolean[20];
  private Handler handler;
  private HUD hud;
  private SecureRandom r = new SecureRandom();

  public Spawn(Handler handler, HUD hud) {
    this.handler = handler;
    this.hud = hud;
  }

  // 1 == 20 // 2 == 40 // 3 == 80 // 4 == 100 // boss = hh*2
  public void tick() {
    if (progress >= 200 && !levelsPassed[0]) {
      handler.clean();
      levelsPassed[0] = true;
      hud.setLevel(hud.getLevel() + 1);
      progress = 0;
      Player.hasShield = true;
      handler.addObject(new RectangleGroup(20, 100, 100, 3, ID.Level1RectangleGroup, handler, hud));
    }
    if (progress >= 400 && !levelsPassed[1]) {
      handler.clean();
      levelsPassed[1] = true;
      hud.setLevel(hud.getLevel() + 1);
      progress = 0;
      progress += 200;
      Player.hasShield = true;
      handler.addObject(
          new SingleMovingSpinningGroup(
              1400, 200, ID.Level1SpinningGroup, 10, 200, 4, 2, handler, hud));
      handler.addObject(
          new SingleMovingSpinningGroup(
              100, 100, ID.Level1SpinningGroup, 10, 200, 4, 2, handler, hud));
    }
    if (progress >= 600 && !levelsPassed[2]) {
      handler.clean();
      levelsPassed[2] = true;
      hud.setLevel(hud.getLevel() + 1);
      progress = 0;
      progress += 600;
      Player.hasShield = true;
      handler.addObject(new TerroristGroup(0, 0, ID.Level1TerroristGroup, 10, handler, hud));
    }
    if (progress >= 800 && !levelsPassed[3]) {
      handler.clean();
      levelsPassed[3] = true;
      hud.setLevel(hud.getLevel() + 1);
      progress = 500;
      Player.hasShield = true;
      handler.addObject(new Boss(400, 400, ID.Boss, 3, 250, 5, 25, handler, hud));
    }
    if (progress >= 1000 && !levelsPassed[4]) {
      handler.clean();
      levelsPassed[4] = true;
      hud.setLevel(hud.getLevel() + 1);
      progress = 520;
      hud.setScore(hud.getScore() + (HUD.coins * 3));
      HUD.coins = 0;

      Player.hasShield = true;
      handler.addObject(
          new SpinningGroup(1536 / 2, 864 / 2, ID.Level1SpinningGroup, 10, 200, 3, handler, hud));
      handler.addObject(
          new SpinningGroup(1536 / 2, 864 / 2, ID.Level2SpinningGroup, 12, 300, 3, handler, hud));
    }
    if (progress >= 1200 && !levelsPassed[5]) {
      handler.clean();
      levelsPassed[5] = true;
      hud.setLevel(hud.getLevel() + 1);
      //            progress = 0;
      progress = 600;

      Player.hasShield = true;
      handler.addObject(new RectangleGroup(20, 100, 20, 4, ID.Level2RectangleGroup, handler, hud));
    }

    if (progress >= 1400 && !levelsPassed[6]) {
      handler.clean();
      levelsPassed[6] = true;
      hud.setLevel(hud.getLevel() + 1);
      //            progress = 0;
      progress = 800;

      Player.hasShield = true;
      handler.addObject(new TerroristGroup(0, 0, ID.Level2TerroristGroup, 20, handler, hud));
    }
    if (progress >= 1600 && !levelsPassed[7]) {
      handler.clean();
      levelsPassed[7] = true;
      hud.setLevel(hud.getLevel() + 1);
      //            progress = 0;//1120
      progress = 280;

      Player.hasShield = true;
      handler.addObject(
          new MultiMovingSpinningGroup(
              50,
              50,
              ID.MMSG,
              ID.Level1SpinningGroup,
              6,
              ID.Level2SpinningGroup,
              10,
              ID.Level1SpinningGroup,
              14,
              ID.Level2SpinningGroup,
              18,
              3,
              handler,
              hud));
    }
    if (progress >= 1800 && !levelsPassed[8]) {
      handler.clean();
      levelsPassed[8] = true;
      hud.setLevel(hud.getLevel() + 1);
      progress = 1000;
      Player.hasShield = true;
      handler.addObject(new Boss(400, 600, ID.Boss, 5, 500, 7, 50, handler, hud));
    }
    if (progress >= 2000 && !levelsPassed[9]) {
      handler.clean();
      levelsPassed[9] = true;
      hud.setLevel(hud.getLevel() + 1);
      hud.setScore(hud.getScore() + (HUD.coins * 3));
      HUD.coins = 0;
      Player.hasShield = true;
      //            progress = 0;
      progress = 200;
      handler.addObject(new RectangleGroup(25, 100, 100, 3, ID.Level3RectangleGroup, handler, hud));
    }
    if (progress >= 2200 && !levelsPassed[10]) {
      handler.clean();
      levelsPassed[10] = true;
      hud.setLevel(hud.getLevel() + 1); // 520 240
      progress = 1000;
      Player.hasShield = true;
      handler.addObject(
          new MultiMovingSpinningGroup(
              0,
              0,
              ID.MMSG,
              ID.Level3SpinningGroup,
              8,
              ID.Level2SpinningGroup,
              12,
              ID.Level1SpinningGroup,
              12,
              ID.Level2SpinningGroup,
              1,
              3,
              handler,
              hud));
    }
    if (progress >= 2400 && !levelsPassed[11]) {
      handler.clean();
      levelsPassed[11] = true;
      hud.setLevel(hud.getLevel() + 1);
      progress = 1120; // 640 + 480
      Player.hasShield = true;
      handler.addObject(
          new SpinningGroup(
              1536 / 2 - 20, 864 / 2 - 20, ID.Level3SpinningGroup, 8, 200, 2, handler, hud));
      handler.addObject(
          new SpinningGroup(
              1536 / 2 - 20, 864 / 2 - 20, ID.Level2SpinningGroup, 12, 300, 2, handler, hud));
      handler.addObject(
          new SpinningGroup(
              1536 / 2 - 20, 864 / 2 - 20, ID.Level1SpinningGroup, 18, 400, 2, handler, hud));
    }
    if (progress >= 2600 && !levelsPassed[12]) {
      handler.clean();
      levelsPassed[12] = true;
      hud.setLevel(hud.getLevel() + 1);
      progress = 1200; // 640 + 480
      Player.hasShield = true;
      handler.addObject(new TerroristGroup(0, 0, ID.Level3TerroristGroup, 20, handler, hud));
    }
    if (progress >= 2800 && !levelsPassed[13]) {
      handler.clean();
      levelsPassed[13] = true;
      hud.setLevel(hud.getLevel() + 1);
      progress = 1500; // 640 + 480
      Player.hasShield = true;
      handler.addObject(new Boss(700, 600, ID.Boss, 7, 750, 9, 75, handler, hud));
    }
    if (progress >= 3000 && !levelsPassed[14]) {
      handler.clean();
      levelsPassed[14] = true;
      hud.setLevel(hud.getLevel() + 1);
      hud.setScore(hud.getScore() + (HUD.coins * 3));
      HUD.coins = 0;
      progress = 700; // 640 + 480
      Player.hasShield = true; // 5 speed
      handler.addObject(new RectangleGroup(25, 100, 100, 5, ID.Level4RectangleGroup, handler, hud));
    }
    if (progress >= 3200 && !levelsPassed[15]) {
      handler.clean();
      levelsPassed[15] = true;
      hud.setLevel(hud.getLevel() + 1);
      progress = 360; // 640 + 480
      Player.hasShield = true; // 1760 800
      handler.addObject(
          new MultiMovingSpinningGroup(
              0,
              0,
              ID.MMSG,
              ID.Level4SpinningGroup,
              8,
              ID.Level3SpinningGroup,
              12,
              ID.Level2SpinningGroup,
              12,
              ID.Level3SpinningGroup,
              10,
              2,
              handler,
              hud));
    }

    if (progress >= 3400 && !levelsPassed[16]) {
      handler.clean();
      levelsPassed[16] = true;
      hud.setLevel(hud.getLevel() + 1);
      progress = 1120; // 720 960
      Player.hasShield = true;
      handler.addObject(
          new SpinningGroup(
              1536 / 2 - 20, 864 / 2 - 20, ID.Level4SpinningGroup, 8, 200, 2, handler, hud));
      handler.addObject(
          new SpinningGroup(
              1536 / 2 - 20, 864 / 2 - 20, ID.Level3SpinningGroup, 12, 300, 2, handler, hud));
      handler.addObject(
          new SpinningGroup(
              1536 / 2 - 20, 864 / 2 - 20, ID.Level2SpinningGroup, 18, 400, 2, handler, hud));
    }
    if (progress >= 3600 && !levelsPassed[17]) {
      handler.clean();
      levelsPassed[17] = true;
      hud.setLevel(hud.getLevel() + 1);
      progress = 1800; // 640 + 480
      Player.hasShield = true;
      handler.addObject(new TerroristGroup(0, 0, ID.Level4TerroristGroup, 20, handler, hud));
    }
    if (progress >= 3800 && !levelsPassed[18]) {
      handler.clean();
      levelsPassed[18] = true;
      hud.setLevel(hud.getLevel() + 1);
      progress = 2000; // 640 + 480
      Player.hasShield = true;
      handler.addObject(new Boss(400, 600, ID.Boss, 9, 1000, 11, 100, handler, hud));
    }
    if (progress >= 4000 && !levelsPassed[19]) {
      handler.clean();
      levelsPassed[19] = true;
      hud.setLevel(hud.getLevel() + 1);
      hud.setScore(hud.getScore() + (HUD.coins * 3));
      HUD.coins = 0;
      Game.gameState = STATE.Won;
      if (!Menu.mute) {
        AudioPlayer.getMusic("Won").play();
      }
    }
  }
}
