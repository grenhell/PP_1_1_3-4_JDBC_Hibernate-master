package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static jm.task.core.jdbc.util.Util.HibernateUtil.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    Session session = null;
    Transaction tx1 = null;



    public UserDaoHibernateImpl() {


    }


    @Override
    public void createUsersTable() {
       try {
           session = getSessionFactory().openSession();
           tx1 = session.beginTransaction();
           String sql = "CREATE TABLE IF NOT EXISTS users " +
                   "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                   "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                   "age TINYINT NOT NULL)";
           Query query = session.createSQLQuery(sql).addEntity(User.class);
           query.executeUpdate();
           tx1.commit();
       } catch (Exception e) {
           tx1.rollback();
           e.printStackTrace();
       } finally {
           if (session != null && session.isOpen()) {
               session.close();
           }
       }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = getSessionFactory().openSession();
            tx1 = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try {
            session = getSessionFactory().openSession();
            tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();
            session.close();
            System.out.println("User "+name+" "+lastName+" saved succesfully");
        } catch (Exception e) {
            tx1.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        try {
            session = getSessionFactory().openSession();
            tx1 = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> UserList = null;
        try {
            session = getSessionFactory().openSession();
            tx1 = session.beginTransaction();
            UserList = session.createQuery("From User").list();
            tx1.commit();
            System.out.println(UserList.toString());
        } catch (Exception e) {
            tx1.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return UserList;
    }


    @Override
    public void cleanUsersTable() {
        try {
            session = getSessionFactory().openSession();
            tx1 = session.beginTransaction();
            String sql = "DELETE FROM Users";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            tx1.commit();
        } catch (Exception e) {
            tx1.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }
}
