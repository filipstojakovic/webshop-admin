package org.etfbl.webshopadmin.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException, ClassNotFoundException, IOException {
        // Initialize the database connection

        InputStream is = getClass().getClassLoader().getResourceAsStream("../config.properties");
        Properties properties = new Properties();
        properties.load(is);
        String url = properties.getProperty("database.url");
        String username = properties.getProperty("database.username");
        String password = properties.getProperty("database.password");

        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    public static DatabaseConnection getInstance() throws SQLException, ClassNotFoundException, IOException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        ResultSet resultSet;
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        return resultSet;
    }
}
