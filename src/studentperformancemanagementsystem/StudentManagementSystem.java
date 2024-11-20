package studentperformancemanagementsystem;

import javax.swing.SwingUtilities;
import view.LoginView;
import dao.AdminAccessDAO;
import dao.StudentAccessDAO;
import view.StudentForm;
import java.sql.Connection;

public class StudentManagementSystem {

    public static void main(String[] args) {
        try {
            // Get a database connection (ensure DatabaseConnection is implemented)
            Connection connection = DatabaseConnection.getConnection();

            // Pass connection to DAO classes
            StudentAccessDAO studentAccessDAO = new StudentAccessDAO(connection);
            AdminAccessDAO adminAccessDAO = new AdminAccessDAO(connection);

            // Launch the LoginView (or any other view to begin)
          SwingUtilities.invokeLater(() -> new LoginView(adminAccessDAO, studentAccessDAO).setVisible(true));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
