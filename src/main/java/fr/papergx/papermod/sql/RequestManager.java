package fr.papergx.papermod.sql;

import fr.papergx.papermod.Main;
import fr.papergx.papermod.sql.enums.REQUEST_TYPE;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RequestManager {

    static Connection con = SqlDataManager.getConnection();


    public static void addData(REQUEST_TYPE type, OfflinePlayer target, String reason, Player operator){
        try{
            switch (type){
                case BAN:
                    PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO bans (name, reason, operator, date) VALUES (?, ?, ?, ?)");
                    preparedStatement.setString(1, target.getName());
                    preparedStatement.setString(2, reason);
                    preparedStatement.setString(3, operator.getName());
                    preparedStatement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

                    preparedStatement.executeUpdate();

                    Main.getBans().put(target.getName(), Arrays.asList(reason, operator.getName()));
                case TEMPBAN:
                    // TODO: Tempban
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeBan(REQUEST_TYPE type, String name) {
        try {
            PreparedStatement statement = con.prepareStatement("delete from " + type.getName() +" where name='" + name + "';");
            statement.executeUpdate();
            statement.close();

            HashMap<String, List<Object>> listBan = (HashMap<String, List<Object>>) Main.getBans();
            listBan.remove(name);
            Main.setBans(listBan);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Object getTable(String table){
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, reason, operator, date from " + table);
            HashMap<String, List<Object>> listGetTable = new HashMap<>();
            while (rs.next()) {
                listGetTable.put(rs.getString("name"), Arrays.asList(rs.getString("reason"),
                        rs.getString("operator"),
                        rs.getTimestamp("date")));
            }
            statement.close();
            return listGetTable;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
