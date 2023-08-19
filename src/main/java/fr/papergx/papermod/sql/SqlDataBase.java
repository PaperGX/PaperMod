package fr.papergx.papermod.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDataBase {

    public static Connection connection;
    public String host, database, username, password;

    public SqlDataBase(String host, String database, String username, String password) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static boolean isConnected() {
        try {
            return !getConnection().isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public void connection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "?autoReconnect=true&allowMultiQueries=true&characterEncoding=utf8", username, password);
    }
}
