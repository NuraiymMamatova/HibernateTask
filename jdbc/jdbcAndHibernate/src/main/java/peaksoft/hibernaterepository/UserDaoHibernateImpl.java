package peaksoft.hibernaterepository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import peaksoft.dao.UserDao;
import peaksoft.model.User;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    static Session session = UtilHibernate.createSessionFactory().openSession();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            session.beginTransaction();
            session.createNativeQuery("""
                    create table if not exists users (
                    id serial primary key,
                    name varchar(50) not null,
                    last_name varchar(50) not null,
                    age smallInt not null);
                    """).executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Table successfully created!");
        } catch (Exception e) {
            System.out.println("Table not created");
        }
    }

    @Override
    public void dropUsersTable() {
        String query = "drop table users;";
        try {
            session.beginTransaction();
            session.createNativeQuery(query).executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("table successfully deleted!");
        } catch (Exception e) {
            System.out.println("table not deleted");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
            System.out.println("Student with name: " + user.getName() + " successfully created!");
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        String query = "delete u from users u where u.id = ?; ";
        try {
            session.beginTransaction();
            session.createNativeQuery(query);
            session.getTransaction().commit();
            session.close();
            System.out.println("User with id: " + id + " successfully deleted");
        } catch (Exception e) {
            System.out.println("user by id not deleted");
        }
    }

    @Override
    public List<User> getAllUsers() {
        session.beginTransaction();
        List<User> users = session.createNativeQuery("select u from users u").getResultList();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session.beginTransaction();
            session.createNativeQuery("truncate table users;").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Table successfully cleaned!");
        } catch (Exception e) {
            System.out.println("table not cleaned");
        }
    }
}
