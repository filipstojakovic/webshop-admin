package org.etfbl.webshopadmin.dao;

import org.etfbl.webshopadmin.config.DatabaseConnection;
import org.etfbl.webshopadmin.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static final String SELECT_ALL = "select * from user";
    public static final String DELETE_BY_ID = "delete from user where id=?";
    public static final String SELECT_BY_ID = SELECT_ALL + " where id=?";

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

    public static boolean delete(Long id) {
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection, DELETE_BY_ID, false, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
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

    private static User extractUser(ResultSet rs) throws SQLException {
        return new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getString(
                "first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("city"));
    }

}
