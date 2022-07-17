package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String DB_URL = "jdbc:mysql://localhost:3306";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "controll";
public Connection getConnection() throws SQLException {
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

}
