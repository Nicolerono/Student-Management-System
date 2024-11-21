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

        
        setTitle("Admin Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

       
        initializeUI();
    }

    private void initializeUI() {
        
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        
        JButton manageStudentsButton = new JButton("Manage Students");
        JButton manageAttendanceButton = new JButton("Manage Attendance");
        JButton manageRecordsButton = new JButton("Manage Records");

        
        manageStudentsButton.addActionListener(e -> {
            StudentForm studentForm = new StudentForm(adminID, "admin", 0);
            studentForm.setVisible(true);
        });

        manageAttendanceButton.addActionListener(e -> {
          StudentForm studentForm = new StudentForm(adminID, "admin", 1); 
            studentForm.setVisible(true);
       });

        manageRecordsButton.addActionListener(e -> {
           StudentForm studentForm = new StudentForm(adminID, "admin", 2);
      
            studentForm.setVisible(true); 
        });

        
        panel.add(manageStudentsButton);
        panel.add(manageAttendanceButton);
        panel.add(manageRecordsButton);

        
        add(panel);
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> new AdminDashboard(1).setVisible(true)); 
    }
}
