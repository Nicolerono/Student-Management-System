package view;

import dao.StudentDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

    private int adminID;
    private StudentDAO studentDAO;

    public AdminDashboard(int adminID) {
        super();
        this.adminID = adminID;
        this.studentDAO = new StudentDAO();

        // Set up the frame
        setTitle("Admin Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize UI components
        initializeUI();
    }

    private void initializeUI() {
        // Create a panel with a grid layout
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create buttons for each feature
        JButton manageStudentsButton = new JButton("Manage Students");
        JButton manageAttendanceButton = new JButton("Manage Attendance");
        JButton manageRecordsButton = new JButton("Manage Records");

        // Add action listeners for buttons
        manageStudentsButton.addActionListener(e -> {
            StudentForm studentForm = new StudentForm(adminID, "admin", 0);
            studentForm.setVisible(true); // Open the Student Form
        });

        manageAttendanceButton.addActionListener(e -> {
          StudentForm studentForm = new StudentForm(adminID, "admin", 1); 
            studentForm.setVisible(true);
       });

        manageRecordsButton.addActionListener(e -> {
           StudentForm studentForm = new StudentForm(adminID, "admin", 2);
      
            studentForm.setVisible(true); 
        });

        // Add buttons to the panel
        panel.add(manageStudentsButton);
        panel.add(manageAttendanceButton);
        panel.add(manageRecordsButton);

        // Add the panel to the frame
        add(panel);
    }

    public static void main(String[] args) {
        // Simulate the Admin Dashboard
        SwingUtilities.invokeLater(() -> new AdminDashboard(1).setVisible(true)); // Example adminID = 1
    }
}
