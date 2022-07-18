package jm.task.core.jdbc.util.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {}
    Connection connection = null;
    public void createUsersTable() {
        try {
            connection = new Util.JDBCUtil().getConnection();
            Statement statement = connection.createStatement();
           // String useBase = "USE userbase"; //указывает базу данных на сервере
            // statement.executeUpdate(useBase); //отправляет команду выбора базы
            String sqlCommand = "CREATE TABLE Users (id BIGINT NOT NULL AUTO_INCREMENT, Name VARCHAR(255), LastName Varchar(255), Age TINYINT, PRIMARY KEY (id))";
            statement.executeUpdate(sqlCommand);
            System.out.println("Table Users created succesfully");
            statement.close();
            System.out.println("statement succesfully closed");
        } catch (SQLException e) {
            System.out.println("Table creation error");
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                    System.out.println("connection succesfully closed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void dropUsersTable() {
        try {
            connection = new Util.JDBCUtil().getConnection();
            Statement statement = connection.createStatement();
          // String useBase = "USE userbase";
         //  statement.executeUpdate(useBase);
            String sqlCommand = "DROP TABLE Users";
            statement.executeUpdate(sqlCommand);
            connection.commit();
            System.out.println("Table Users deleted succesfully");
            statement.close();
            System.out.println("statement succesfully closed");
        } catch (SQLException e) {
            System.out.println("Table delete error");
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                    System.out.println("connection succesfully closed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO Users (Name, LastName, Age) VALUES (?, ?, ?)";
        try {
            connection = new Util.JDBCUtil().getConnection();
            Statement statement = connection.createStatement();
          //  String useBase = "USE userbase";
           // statement.executeUpdate(useBase);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User "+name+" "+lastName+" saved succesfully");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("User safe error");
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                    System.out.println("connection succesfully closed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM Users WHERE ID=?";
        try {
            connection = new Util.JDBCUtil().getConnection();
            Statement statement = connection.createStatement();
           // String useBase = "USE userbase";
           // statement.executeUpdate(useBase);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User removed succesfully");
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("User remove error");
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                    System.out.println("connection succesfully closed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> UsersList = new ArrayList<>();
        try {
            connection = new Util.JDBCUtil().getConnection();
            Statement statement = connection.createStatement();
           // String useBase = "USE userbase";
           // statement.executeUpdate(useBase);
            String SQL = "SELECT * FROM USERS";
            ResultSet resultSet = statement.executeQuery(SQL);
            connection.commit();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                UsersList.add(user);
                System.out.println("User "+ user);
            }

        } catch (SQLException e) {
            System.out.println("getUserList error");
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                    System.out.println("connection succesfully closed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return UsersList;
    }

    public void cleanUsersTable() {
        try {
            connection = new Util.JDBCUtil().getConnection();
            Statement statement = connection.createStatement();
           // String useBase = "USE userbase";
           // statement.executeUpdate(useBase);
            String SQL = "DELETE FROM USERS";
            statement.executeUpdate(SQL);
            connection.commit();
            System.out.println("Users cleaned succesfully");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Users clean error");
            e.printStackTrace();
        } finally {
            if (connection != null){
                try {
                    connection.close();
                    System.out.println("connection succesfully closed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
