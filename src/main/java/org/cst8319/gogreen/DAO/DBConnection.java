package org.cst8319.gogreen.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBConnection {

    private static final String PROPERTIES_FILE = "db.properties";
    private static Properties properties = new Properties();
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    static {
        try (InputStream input = new FileInputStream(PROPERTIES_FILE)) {

            properties.load(input);

            Class.forName(DRIVER_CLASS);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password")
        );
    }

    public static void closeConnection(Connection conn) {

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}