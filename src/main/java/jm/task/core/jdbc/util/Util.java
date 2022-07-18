package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/userbase";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "controll";

    public static class JDBCUtil {
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

    public static class HibernateUtil {
        private static StandardServiceRegistry registry;
        public static SessionFactory sessionFactory;

        public static SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                ServiceRegistry serviceRegistry = null;
                try {
                    Configuration configuration = new Configuration();
                    Properties settings = new Properties();
                    settings.put(Environment.DRIVER, DB_DRIVER);
                    settings.put(Environment.URL, DB_URL);
                    settings.put(Environment.USER, DB_USERNAME);
                    settings.put(Environment.PASS, DB_PASSWORD);
                    settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                    settings.put(Environment.SHOW_SQL, "true");
                    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                    settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                    configuration.setProperties(settings);
                    configuration.addAnnotatedClass(User.class);
                    serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();
                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                    System.out.println("Connection OK");
                } catch (Exception e) {
                    System.out.println("Connection ERROR");
                    e.printStackTrace();
                    if (serviceRegistry != null) {
                        StandardServiceRegistryBuilder.destroy(serviceRegistry);
                    }
                }

            }
            return sessionFactory;

            }
        /*public static void shutdown() {
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }*/

    }
}





