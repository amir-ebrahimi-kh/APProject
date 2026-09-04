/**
 * A standalone Java exercise component.
 */
package extras;

import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
  private static Map<String, Sound> soundMap = new HashMap<String, Sound>();
  private static Map<String, Music> musicMap = new HashMap<String, Music>();

  public static void load() {
    try {
      //            musicMap.put("game1", new Music("res/SpaceHeroes.ogg"));
      musicMap.put("Won", new Music("res/Won.ogg"));
      soundMap.put("bullet1", new Sound("res/L1.ogg"));
      soundMap.put("bullet2", new Sound("res/L2.ogg"));
      soundMap.put("bullet3", new Sound("res/L3.ogg"));
      musicMap.put("menu_music", new Music("res/menu_music.ogg"));
      musicMap.put("game_over", new Music("res/gameover.ogg"));
      soundMap.put("expo", new Sound("res/expo.ogg"));
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }

  public static Music getMusic(String key) {
    return musicMap.get(key);
  }

  public static Sound getSound(String key) {
    return soundMap.get(key);
  }
}
