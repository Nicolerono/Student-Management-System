package view;

import javax.swing.*;
import java.awt.*;
import dao.StudentDAO;

public class StudentDashboard extends JFrame{
    private StudentDAO studentDAO;
    private JButton viewAttendanceButton;
    private JButton viewRecordsButton;
    String role;

    public StudentDashboard(String role, int studentID) {
        studentDAO = new StudentDAO();
this.role = role;
       
        setTitle("Student Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        viewAttendanceButton = new JButton("View Attendance");
        viewRecordsButton = new JButton("View Records");

        
         viewAttendanceButton.addActionListener(e -> {
            JFrame frame = new JFrame("Attendance Records");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(this); 

           
            AttendanceView attendanceView = new AttendanceView(studentID,role, studentDAO);
            frame.add(attendanceView); 
            frame.setVisible(true); 
        });

      
       
        viewRecordsButton.addActionListener(e -> openStudentRecordsWindow(studentID));

        
        panel.add(viewAttendanceButton);
        panel.add(viewRecordsButton);

        
        add(panel);
    }

    private StudentDashboard() {
       
    }

    private void openAttendanceWindow(int  studentID) {
        JFrame frame = new JFrame("Attendance Records");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(this);

        AttendanceView attendanceView = new AttendanceView( studentID,role, studentDAO);
        if (attendanceView instanceof JPanel) {
            frame.add(attendanceView); 
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(
                this,
                "AttendanceView is not a valid JPanel.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

  private void openStudentRecordsWindow(int studentID) {
      
       JFrame frame = new JFrame("Student Records");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(this);

    StudentRecordsView recordsView = new StudentRecordsView(role, studentID, studentDAO);
    frame.add(recordsView);
    frame.setVisible(true);
    try {
        
        
        recordsView.setVisible(true);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(
            this,
            "Error opening Student Records: " + ex.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        ex.printStackTrace();
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentDashboard().setVisible(true));
    }
}
