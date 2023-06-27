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

    public static List<User> findAll(){
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

    private static User extractUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("city")
                );

    }
}
