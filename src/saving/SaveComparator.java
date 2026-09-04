/**
 * A standalone Java exercise component.
 */
package saving;

import java.util.Comparator;

public class SaveComparator implements Comparator<Save> {
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
