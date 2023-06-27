package org.etfbl.webshopadmin.dao;

import org.etfbl.webshopadmin.config.DatabaseConnection;
import org.etfbl.webshopadmin.dto.UserAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAdminDao {

    public static final String SELECT_BY_USERNAME = "select * from user_admin where username=?";

    public static UserAdmin selectByUsername(String username) {
        UserAdmin user = null;
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = DAOUtil.prepareStatement(connection, SELECT_BY_USERNAME, false, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = getUserFromResultSet(rs);
            }
            statement.close();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return user;
    }

    public static UserAdmin getUserFromResultSet(ResultSet rs) throws SQLException {
        return getUserFromResultSet(rs, "id");
    }

    public static UserAdmin getUserFromResultSet(ResultSet rs, String idColumnName) throws SQLException {
        return new UserAdmin(rs.getLong(idColumnName), rs.getString("username"), rs.getString("password"), rs.getString(
                "first_name"), rs.getString("last_name"));
    }
}
