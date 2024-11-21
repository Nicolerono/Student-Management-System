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
           
            Connection connection = DatabaseConnection.getConnection();

            
            StudentAccessDAO studentAccessDAO = new StudentAccessDAO(connection);
            AdminAccessDAO adminAccessDAO = new AdminAccessDAO(connection);

            
          SwingUtilities.invokeLater(() -> new LoginView(adminAccessDAO, studentAccessDAO).setVisible(true));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
