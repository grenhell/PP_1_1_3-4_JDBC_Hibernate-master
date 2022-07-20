package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    public static final String DB_DRIVER = "com.mysql.cj.dbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/userbase";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "controll";
public static Connection getConnection() throws SQLException {
Connection connection = null;
try {
    connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    System.out.println("Connection OK");
    connection.setAutoCommit(false);
} catch (SQLException e) {
    e.printStackTrace();
    System.out.println("Connection ERROR");
}
    return connection;
}
public static void closeConnection (Connection connection) {
    if (connection != null) {
        try {
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
public static <T extends Statement> void  closeStatement  (T statement) {
    if (statement != null) {
        try {
            statement.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

}
