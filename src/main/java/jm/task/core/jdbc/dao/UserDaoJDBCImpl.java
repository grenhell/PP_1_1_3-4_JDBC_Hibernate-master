package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;
import static jm.task.core.jdbc.util.Util.JDBCUtil.*;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {}
    Connection connection = null;
    public void createUsersTable() {
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String sqlCommand = "CREATE TABLE Users (id BIGINT NOT NULL AUTO_INCREMENT, Name VARCHAR(255), LastName Varchar(255), Age TINYINT, PRIMARY KEY (id))";
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

    }

    public void dropUsersTable() {
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String sqlCommand = "DROP TABLE Users";
            statement.executeUpdate(sqlCommand);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO Users (Name, LastName, Age) VALUES (?, ?, ?)";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User "+name+" "+lastName+" saved succesfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);

        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM Users WHERE ID=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    public List<User> getAllUsers() {
        List<User> UsersList = new ArrayList<>();
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
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
            }
            System.out.println(UsersList);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return UsersList;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String SQL = "DELETE FROM USERS";
            statement.executeUpdate(SQL);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }
}