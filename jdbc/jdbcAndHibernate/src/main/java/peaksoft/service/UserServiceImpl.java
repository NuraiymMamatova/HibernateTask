package peaksoft.service;

import peaksoft.hibernaterepository.UserDaoHibernateImpl;
import peaksoft.jdbcrepository.UserDaoJdbcImpl;
import peaksoft.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
//
//    public void createUsersTable() throws SQLException {
//        userDaoHibernate.createUsersTable();
//    }
//
//    public void dropUsersTable() {
//        userDaoHibernate.dropUsersTable();
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        userDaoHibernate.saveUser(name, lastName, age);
//    }
//
//    public void removeUserById(long id) {
//       userDaoHibernate.removeUserById(id);
//    }
//
//    public List<User> getAllUsers() {
//
//        return userDaoHibernate.getAllUsers();
//    }
//
//    public void cleanUsersTable() {
//       userDaoHibernate.cleanUsersTable();
//    }

    public void createUsersTable() throws SQLException {
       userDaoJdbc.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoJdbc.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJdbc.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoJdbc.removeUserById(id);
    }

    public List<User> getAllUsers() {

        return userDaoJdbc.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJdbc.cleanUsersTable();
    }
}
