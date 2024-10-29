package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection con = Util.getConnection(); Statement statement = con.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(50)," +
                    "lastName VARCHAR(50)," +
                    "age TINYINT," +
                    "PRIMARY KEY (id))");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection con = Util.getConnection(); Statement statement = con.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = Util.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO users (name, lastName, age) VALUES (?,? ,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection con = Util.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(
                     "DELETE FROM users WHERE id=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = Util.getConnection(); Statement statement = con.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            while (result.next()) {
                long id = result.getLong("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                Byte age = result.getByte("age");
                users.add(new User(id, name, lastName, age));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection con = Util.getConnection(); Statement statement = con.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
