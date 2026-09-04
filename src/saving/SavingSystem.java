/**
 * A standalone Java exercise component.
 */
package saving;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SavingSystem {

    private static final String URL = "jdbc:sqlite:game.data.db";

    static {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS saves (\n"
                    + " player text PRIMARY KEY,\n"
                    + " game_level integer,\n"
                    + " score integer,\n"
                    + " bullet integer,\n"
                    + " bullet_level integer,\n"
                    + " bombs integer,\n"
                    + " lifes integer,\n"
                    + " coins integer,\n"
                    + " maxTemp integer\n"
                    + ");";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Save load(String player) {
        String sql = "SELECT * FROM saves WHERE player = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Stats stats = new Stats();
                stats.game_level = rs.getInt("game_level");
                stats.score = rs.getInt("score");
                stats.bullet = rs.getInt("bullet");
                stats.bullet_level = rs.getInt("bullet_level");
                stats.bombs = rs.getInt("bombs");
                stats.lifes = rs.getInt("lifes");
                stats.coins = rs.getInt("coins");
                stats.maxTemp = rs.getInt("maxTemp");
                return new Save(player, stats);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Save(player);
    }

    public static void save(Save save) {
        String sql = "INSERT INTO saves (player, game_level, score, bullet, bullet_level, bombs, lifes, coins, maxTemp) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) "
                   + "ON CONFLICT(player) DO UPDATE SET "
                   + "game_level=excluded.game_level, score=excluded.score, bullet=excluded.bullet, "
                   + "bullet_level=excluded.bullet_level, bombs=excluded.bombs, lifes=excluded.lifes, "
                   + "coins=excluded.coins, maxTemp=excluded.maxTemp";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, save.player);
            pstmt.setInt(2, save.stats.game_level);
            pstmt.setInt(3, save.stats.score);
            pstmt.setInt(4, save.stats.bullet);
            pstmt.setInt(5, save.stats.bullet_level);
            pstmt.setInt(6, save.stats.bombs);
            pstmt.setInt(7, save.stats.lifes);
            pstmt.setInt(8, save.stats.coins);
            pstmt.setInt(9, save.stats.maxTemp);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePlayerName(String player) {
        String sql = "DELETE FROM saves WHERE player = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, player);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addPlayerName(String player) {
        if (!player.isEmpty()) {
            String sql = "INSERT OR IGNORE INTO saves (player, game_level, score, bullet, bullet_level, bombs, lifes, coins, maxTemp) "
                       + "VALUES(?, 1, 0, 0, 1, 3, 5, 0, 100)";
            try (Connection conn = DriverManager.getConnection(URL);
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, player);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isValid(String name) {
        String sql = "SELECT 1 FROM saves WHERE player = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int size() {
        String sql = "SELECT COUNT(*) AS total FROM saves";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getListPlayers() {
        StringBuilder st = new StringBuilder();
        String sql = "SELECT player FROM saves";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                st.append(rs.getString("player")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st.toString();
    }

    public static String getHallOfFame(int i) {
        String sql = "SELECT player, game_level, score FROM saves ORDER BY game_level DESC, score DESC LIMIT 1 OFFSET ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, i);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("player")
                        + "                                         "
                        + rs.getInt("game_level")
                        + "                                            "
                        + rs.getInt("score");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
