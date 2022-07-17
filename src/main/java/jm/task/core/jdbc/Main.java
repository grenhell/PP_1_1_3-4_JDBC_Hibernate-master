package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl use = new UserServiceImpl();
       //use.dropUsersTable();
        //use.createUsersTable();
       /* use.saveUser("Jake", "Sally", (byte) 18);
        use.saveUser("Joe", "Sally", (byte) 40);
        use.saveUser("Jon", "Sally", (byte) 70);
        use.saveUser("Rick", "Sally", (byte) 22);*/
        //use.removeUserById(2);
        //use.getAllUsers();
    }
}
