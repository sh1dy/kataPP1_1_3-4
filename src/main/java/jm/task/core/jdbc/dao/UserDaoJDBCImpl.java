package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection conn = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not EXISTS users (" +
                    "id bigint primary key AUTO_INCREMENT," +
                    "name varchar(50)," +
                    "lastName varchar(50)," +
                    "age tinyint);");
        } catch (SQLException i) {
            throw new RuntimeException();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("drop table if exists users;");
        } catch (SQLException did) {
            throw new RuntimeException();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement stmt = conn.prepareStatement("insert into users (name, lastName, age) " +
                     "values (?, ?, ?);")) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.execute();
            System.out.println("User с именем — " + name + " добавлен(a) в базу данных");
        } catch (SQLException it) {
            throw new RuntimeException();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement stmt = conn.prepareStatement("delete from users where id = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException again) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement("select * from users");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3),
                        rs.getByte(4));
                user.setId(rs.getLong(1));
                users.add(user);
                System.out.println(user);
            }
        } catch (SQLException i) {
            throw new RuntimeException();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("delete from users;");
        } catch (SQLException played) {
            throw new RuntimeException();
        }
    }
}
