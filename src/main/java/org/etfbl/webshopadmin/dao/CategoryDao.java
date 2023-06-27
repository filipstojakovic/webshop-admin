package org.etfbl.webshopadmin.dao;

import org.etfbl.webshopadmin.config.DatabaseConnection;
import org.etfbl.webshopadmin.dto.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    public static final String SELECT_ALL = "select * from category";

    public static List<Category> findAll(){
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

    private static Category extractCategory(ResultSet rs) throws SQLException {
        return new Category(rs.getLong("id"), rs.getString("name"), rs.getLong("parent_category_id"));

    }
}
