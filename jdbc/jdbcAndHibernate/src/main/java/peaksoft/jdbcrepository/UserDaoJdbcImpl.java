package peaksoft.jdbcrepository;

import peaksoft.dao.UserDao;
import peaksoft.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    private static final Connection connection = Util.getConnection();

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String query = """
                create table  users(
                id serial primary key,
                name varchar not null,
                last_name varchar not null,
                age smallint not null);
                """;
        try (Statement createTable = connection.createStatement()) {
            createTable.executeUpdate(query);
            System.out.println("Table users successfully created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void dropUsersTable() {
        String query = "drop table  users;";
        try (Statement dropTable = connection.createStatement()) {
            dropTable.executeUpdate(query);
            System.out.println("Table users successfully deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "insert into users(name, last_name, age) values (?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User with name " + name + " successfully saved");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String query = "delete from users where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            System.out.println("User with id " + id + " successfully deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String query = "select * from users;";
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4))
                );
                users.forEach(x -> {
                    try {
                        System.out.println("ID: " + resultSet.getInt(1) + "\nName: " + x.getName() + "\nLast name: " + x.getLastName() + "\nAge: " + x.getAge() + "\n");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users.stream().toList();
    }

    public void cleanUsersTable() {
        String query = "truncate table users;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            System.out.println("Table cleared");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}