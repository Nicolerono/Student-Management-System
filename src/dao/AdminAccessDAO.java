package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminAccessDAO {
    private Connection connection;

    public AdminAccessDAO() {
       
    }

 
    public AdminAccessDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean validateAdmin(String adminID, String password) {
        String query = "SELECT * FROM admin_access WHERE admin_ID = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, adminID); 
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
}
