/**
 * A standalone Java exercise component.
 */
package saving;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class SavingSystem {

  private static File file = new File("game.data.txt");
  private static ArrayList<Save> saves = new ArrayList<Save>();
  private static Type collectionType = new TypeToken<ArrayList<Save>>() {}.getType();

  private static void readFromFile() throws Exception {

    Scanner loader = new Scanner(file);
    String st = loader.nextLine();
    loader.close();
    saves = new Gson().fromJson(st, collectionType);
  }

  private static void writeToFile() {
    String st = new Gson().toJson(saves);
    try {
      PrintWriter pw = new PrintWriter(file);
      pw.print(st);
      pw.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static Save load(String player) {

    try {
      readFromFile();
    } catch (Exception e) {
      e.printStackTrace();
    }
    Save newSave = new Save(player);
    for (int index = saves.size() - 1; index >= 0; index--) {
      if (saves.get(index).player.equals(player)) {
        newSave = saves.get(index);
        break;
      }
    }
    return newSave;
  }

  public static void save(Save save) {

    boolean saveUpdated = false;
    boolean firstSave = false;
    try {
      readFromFile();
    } catch (Exception e) {
      firstSave = true;
    }
    if (firstSave) {
      saves.clear();
      saves.add(save);
    } else {
      for (Save save2 : saves) {
        if (save2.player.equals(save.player)) {
          save2.stats = save.stats;
          saveUpdated = true;
        }
      }
      if (!saveUpdated) {
        saves.add(save);
      }
    }
    writeToFile();
  }

  private static ArrayList<String> loadPlayerNames() {

    boolean firstLoad = false;
    try {
      readFromFile();
    } catch (Exception e1) {
      firstLoad = true;
    }
    ArrayList<String> ss = new ArrayList<String>();
    if (!firstLoad) {
      for (Save save : saves) {
        ss.add(save.player);
      }
    }
    return ss;
  }

  public static void deletePlayerName(String player) {

    try {
      readFromFile();
    } catch (Exception e) {
    }
    ArrayList<Save> saves2 = new ArrayList<Save>();

    for (Save save : saves) {
      if (!save.player.equals(player)) {
        saves2.add(save);
      }
    }
    saves = saves2;
    writeToFile();
  }

  public static void addPlayerName(String player) {
    if (!player.equals("")) {
      boolean alreadyExists = false;
      boolean firstSave = false;
      try {
        readFromFile();
      } catch (Exception e) {
        firstSave = true;
      }
      if (firstSave) {
        saves.clear();
        saves.add(new Save(player));
      } else {
        for (Save save2 : saves) {
          if (save2.player.equals(player)) {
            alreadyExists = true;
            break;
          }
        }
        if (!alreadyExists) {
          saves.add(new Save(player));
        }
      }
      writeToFile();
    }
  }

  public static boolean isValid(String name) {
    boolean firstrun = false;
    boolean isin = false;
    try {
      readFromFile();
    } catch (Exception e) {
      firstrun = true;
    }
    if (!firstrun) {
      for (Save save : saves) {
        if (save.player.equals(name)) {
          isin = true;
          break;
        }
      }
    }
    return isin;
  }

  public static int size() {
    return saves.size();
  }

  public static String getListPlayers() {
    ArrayList<String> s = loadPlayerNames();
    String st = "";
    if (s.size() > 0) {
      for (int i = 0; i < s.size(); i++) {
        st = st + s.get(i) + "\n";
      }
    }
    return st;
  }

  public static String getHallOfFame(int i) {
    try {
      readFromFile();
    } catch (Exception e) {
      e.printStackTrace();
    }
    ArrayList<Save> s = saves;
    s.sort(new SaveComparator());
    return s.get(i).player
        + "                                         "
        + s.get(i).stats.game_level
        + "                                            "
        + s.get(i).stats.score;
  }
}

class SaveComparator implements Comparator<Save> {
  @Override
  public int compare(Save o1, Save o2) {
    if (o1.stats.game_level > o2.stats.game_level) return -1;
    else if (o1.stats.game_level < o2.stats.game_level) return 1;
    else {
      if (o1.stats.score > o2.stats.score) return -1;
      else if (o1.stats.score < o2.stats.score) return 1;
      else return 0;
    }
  }
}
