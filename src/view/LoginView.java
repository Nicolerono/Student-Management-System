package view;

import dao.AdminAccessDAO;
import dao.StudentAccessDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class LoginView extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;
    private JComboBox<String> roleSelector;
    private JButton loginButton;
       private int loggedInStudentID;

    private AdminAccessDAO adminAccessDAO;
    private StudentAccessDAO studentAccessDAO;

    public LoginView(AdminAccessDAO adminAccessDAO, StudentAccessDAO studentAccessDAO) {
        this.adminAccessDAO = adminAccessDAO;
        this.studentAccessDAO = studentAccessDAO;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JLabel roleLabel = new JLabel("Role:");
        roleSelector = new JComboBox<>(new String[]{"Admin", "Student"});

        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());

        panel.add(idLabel);
        panel.add(idField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(roleLabel);
        panel.add(roleSelector);
        panel.add(new JLabel()); // Empty placeholder
        panel.add(loginButton);

        add(panel);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idField.getText();
            String password = new String(passwordField.getPassword());
            String role = (String) roleSelector.getSelectedItem();

            // Admin role login
            if ("Admin".equals(role)) {
                try {
                    if (adminAccessDAO.validateAdmin(id, password)) {
                        JOptionPane.showMessageDialog(LoginView.this, "Admin login successful!");
                        int adminID = Integer.parseInt(id);
                        new AdminDashboard(adminID).setVisible(true);
                        dispose(); // Close the login window
                    } else {
                        JOptionPane.showMessageDialog(LoginView.this, "Invalid admin credentials.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginView.this, "Error validating admin credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            // Student role login
            else if ("Student".equals(role)) {
                try {
                    if (studentAccessDAO.validateStudent(id, password)) {
                        JOptionPane.showMessageDialog(LoginView.this, "Student login successful!");
                       
                        int studentID = Integer.parseInt(id);
                        loggedInStudentID = studentID;
               SwingUtilities.invokeLater(() -> new StudentDashboard(role, studentID).setVisible(true));
                        dispose(); // Close the login window
                    } else {
                        JOptionPane.showMessageDialog(LoginView.this, "Invalid student credentials.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginView.this, "Error validating student credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Set up the connection and DAOs
        Connection connection = new dao.DatabaseConnection().getConnection();  // Ensure DatabaseConnection is implemented correctly
        AdminAccessDAO adminAccessDAO = new AdminAccessDAO(connection);
        StudentAccessDAO studentAccessDAO = new StudentAccessDAO(connection);

        // Start the LoginView UI
        SwingUtilities.invokeLater(() -> new LoginView(adminAccessDAO, studentAccessDAO).setVisible(true));
    }
}
