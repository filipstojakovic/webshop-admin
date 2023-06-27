package org.etfbl.webshopadmin.dao;

import org.etfbl.webshopadmin.config.DatabaseConnection;
import org.etfbl.webshopadmin.dto.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static final String SELECT_ALL = "select * from user";
    public static final String DELETE_BY_ID = "delete from user where id=?";
    public static final String SELECT_BY_ID = SELECT_ALL + " where id=?";

    public static final String INSERT_USER =
            "insert into user (username, password, first_name, last_name, email, " + "city, role_id, avatar_path) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER = "update user set username=?, first_name=?, last_name=?, " +
            "email=?, city=? where id=?";

    public static List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = extractUser(rs);
                users.add(user);
            }
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public static User findById(Long id) {
        User user = null;
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection, SELECT_BY_ID, false, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public static User updateUser(Long id,
                                  String username,
                                  String firstname,
                                  String lastname,
                                  String email,
                                  String city) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = DatabaseConnection.getInstance().getConnection();
        Object[] values = {username,firstname,lastname,email,city, id};
        PreparedStatement statement = DAOUtil.prepareStatement(connection, UPDATE_USER, true, values);
        int result = statement.executeUpdate();
        statement.close();
        return findById(id);
    }

    public static void insertUser(User user) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        Object[] values = {user.getUsername(), encryptedPassword, user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getCity(), 1L, null};
        PreparedStatement statement = DAOUtil.prepareStatement(connection, INSERT_USER, true, values);
        int result = statement.executeUpdate();
        statement.close();
    }

    public static boolean delete(Long id) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection, DELETE_BY_ID, false, id);
            int result = statement.executeUpdate();
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private static User extractUser(ResultSet rs) throws SQLException {
        return new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getString(
                "first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("city"));
    }

}
