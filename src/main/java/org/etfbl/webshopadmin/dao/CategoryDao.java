package org.etfbl.webshopadmin.dao;

import org.etfbl.webshopadmin.config.DatabaseConnection;
import org.etfbl.webshopadmin.dto.Category;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    public static final String SELECT_ALL = "select * from category";
    public static final String INSERT_CATEGORY = "insert into category (name, parent_category_id) " + "VALUES (?, ?)";
    public static final String DELETE_BY_ID = "delete from category where id=?";

    //SELECT c.id, c.name AS category_name, p.name AS parent_category_name
    //FROM category c
    //LEFT JOIN category p ON c.parent_category_id = p.id;

    public static List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Category category = extractCategory(rs);
                categories.add(category);
            }
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return categories;
    }

    public static void insertCategory(String name, Long parentId) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Object[] values = {name, parentId};
        PreparedStatement statement = DAOUtil.prepareStatement(connection, INSERT_CATEGORY, true, values);
        int result = statement.executeUpdate();
        statement.close();
    }

    public static void delete(Long categoryId) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement statement = DAOUtil.prepareStatement(connection, DELETE_BY_ID, false, categoryId);
        int result = statement.executeUpdate();
        statement.close();
    }

    private static Category extractCategory(ResultSet rs) throws SQLException {
        return new Category(rs.getLong("id"), rs.getString("name"), rs.getLong("parent_category_id"));

    }
}
