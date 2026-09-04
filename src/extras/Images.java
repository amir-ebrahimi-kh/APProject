/**
 * A standalone Java exercise component.
 */
package extras;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Images {

  private static Map<String, BufferedImage> imageMap = new HashMap<String, BufferedImage>();

  public static void load() {

    imageMap.put("bluebul", ImageLoader.loadImage("res/bluebul.png"));
    imageMap.put("pinkbul", ImageLoader.loadImage("res/pinkbul.png"));
    imageMap.put("goldbul", ImageLoader.loadImage("res/goldbul.png"));
    imageMap.put("redbul", ImageLoader.loadImage("res/redbul.png"));
    imageMap.put("silver", ImageLoader.loadImage("res/bolt_silver.png"));
    imageMap.put("gold", ImageLoader.loadImage("res/bolt_gold.png"));
    imageMap.put("bronze", ImageLoader.loadImage("res/bolt_bronze.png"));
    imageMap.put("powerup_level", ImageLoader.loadImage("res/pill_blue.png"));
    imageMap.put("powerup_heat", ImageLoader.loadImage("res/pill_red.png"));
    imageMap.put("coin", ImageLoader.loadImage("res/coin.png"));
    imageMap.put("bplayer1", ImageLoader.loadImage("res/1.png"));
    imageMap.put("bplayer2", ImageLoader.loadImage("res/2.png"));
    imageMap.put("bplayer3", ImageLoader.loadImage("res/3.png"));
    imageMap.put("bplayer4", ImageLoader.loadImage("res/4.png"));
    imageMap.put("bplayer5", ImageLoader.loadImage("res/5.png"));
    imageMap.put("rplayer1", ImageLoader.loadImage("res/6.png"));
    imageMap.put("rplayer2", ImageLoader.loadImage("res/7.png"));
    imageMap.put("rplayer3", ImageLoader.loadImage("res/8.png"));
    imageMap.put("rplayer4", ImageLoader.loadImage("res/9.png"));
    imageMap.put("rplayer5", ImageLoader.loadImage("res/10.png"));
    imageMap.put("back_ground", ImageLoader.loadImage("res/newnewback.png"));
    imageMap.put("back_ground2", ImageLoader.loadImage("res/newnewback.png"));
    imageMap.put("bomb1", ImageLoader.loadImage("res/bomb1.png"));
    imageMap.put("bomb2", ImageLoader.loadImage("res/bomb2.png"));
    imageMap.put("bomb3", ImageLoader.loadImage("res/bomb3.png"));
    imageMap.put("exp0", ImageLoader.loadImage("res/1_0.png"));
    imageMap.put("exp1", ImageLoader.loadImage("res/1_1.png"));
    imageMap.put("exp2", ImageLoader.loadImage("res/1_2.png"));
    imageMap.put("exp3", ImageLoader.loadImage("res/1_3.png"));
    imageMap.put("exp4", ImageLoader.loadImage("res/1_4.png"));
    imageMap.put("exp5", ImageLoader.loadImage("res/1_5.png"));
    imageMap.put("exp6", ImageLoader.loadImage("res/1_6.png"));
    imageMap.put("exp7", ImageLoader.loadImage("res/1_7.png"));
    imageMap.put("exp8", ImageLoader.loadImage("res/1_8.png"));
    imageMap.put("exp9", ImageLoader.loadImage("res/1_9.png"));
    imageMap.put("exp10", ImageLoader.loadImage("res/1_10.png"));
    imageMap.put("exp11", ImageLoader.loadImage("res/1_11.png"));
    imageMap.put("exp12", ImageLoader.loadImage("res/1_12.png"));
    imageMap.put("exp13", ImageLoader.loadImage("res/1_13.png"));
    imageMap.put("exp14", ImageLoader.loadImage("res/1_14.png"));
    imageMap.put("exp15", ImageLoader.loadImage("res/1_15.png"));
    imageMap.put("exp16", ImageLoader.loadImage("res/1_16.png"));
    imageMap.put("enemy1", ImageLoader.loadImage("res/enemy1.png"));
    imageMap.put("enemy2", ImageLoader.loadImage("res/enemy2.png"));
    imageMap.put("enemy3", ImageLoader.loadImage("res/enemy3.png"));
    imageMap.put("enemy4", ImageLoader.loadImage("res/enemy4.png"));
    imageMap.put("boss1", ImageLoader.loadImage("res/boss1.png"));
    imageMap.put("boss2", ImageLoader.loadImage("res/boss2.png"));
    imageMap.put("boss3", ImageLoader.loadImage("res/boss3.png"));
    imageMap.put("boss4", ImageLoader.loadImage("res/boss4.png"));
    imageMap.put("boss5", ImageLoader.loadImage("res/boss5.png"));
    imageMap.put("boss6", ImageLoader.loadImage("res/boss6.png"));
    imageMap.put("boss7", ImageLoader.loadImage("res/boss7.png"));
    imageMap.put("boss8", ImageLoader.loadImage("res/boss8.png"));
  }

  public static BufferedImage getImage(String key) {
    return imageMap.get(key);
  }
}
