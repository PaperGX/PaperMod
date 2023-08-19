package fr.papergx.papermod.sql;

import fr.papergx.papermod.Main;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlDataManager {
    public static SqlDataBase database;
    public static Main plugin = JavaPlugin.getPlugin(Main.class);

    public SqlDataManager(Main main) {
    }

    public static Connection getConnection() {
     String host = plugin.getConfig().getString("MySQL.host");
     String db = plugin.getConfig().getString("MySQL.database");
     String user = plugin.getConfig().getString("MySQL.user");
     String password = plugin.getConfig().getString("MySQL.password");
        if (database == null
                || !database.isConnected()) {
            database = new SqlDataBase(host, db, user, password);
            try {
                database.connection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return SqlDataBase.getConnection();
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `bans`.`bans` (" +
                " `id` INT NOT NULL AUTO_INCREMENT ," +
                " `name` VARCHAR(32) NOT NULL ," +
                " `reason` TEXT NOT NULL ," +
                " `operator` VARCHAR(32) NOT NULL ," +
                " `date` TIMESTAMP NOT NULL ," +
                " PRIMARY KEY (`id`));";
        try (Connection conn = SqlDataBase.getConnection()) {
            conn.createStatement().execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        if (database != null) {
            try {
                SqlDataBase.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
