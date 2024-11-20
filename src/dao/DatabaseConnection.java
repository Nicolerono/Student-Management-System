package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";  // Replace with your database details
    private static final String USER = "root";  // Database username
    private static final String PASSWORD = "";  // Database password (change if needed)

    // Method to establish a connection
    public Connection getConnection() {
        try {
            // Register the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
