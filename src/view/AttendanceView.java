package view;

import dao.StudentDAO;
import model.Attendance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class AttendanceView extends JPanel {
    private int studentID;
    private String role; // Role of the user (admin or student)
    private JTable attendanceTable;
    private StudentDAO studentDAO;

    // Constructor for role-based attendance view
    public AttendanceView( int studentID, String role, StudentDAO studentDAO) {
        this.role = role;
        this.studentID = studentID;
        this.studentDAO = studentDAO;

        initUI();
        loadAttendanceRecords(); // Load attendance data based on the role
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Attendance Records", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        add(header, BorderLayout.NORTH);

        // Create a table to display attendance records
        attendanceTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadAttendanceRecords() {
        List<Attendance> attendanceRecords;

        // Fetch attendance records based on the role
        if (role.equalsIgnoreCase("admin")) {
            attendanceRecords = studentDAO.getAllAttendanceRecords(); // Fetch all records for admin
        } else {
            attendanceRecords = studentDAO.getAttendanceRecordsByStudentId(studentID); // Fetch records for a specific student
        }

        // Populate the table model
        String[] columnNames = {"Student ID", "Attendance Date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Attendance attendance : attendanceRecords) {
            model.addRow(new Object[]{
                attendance.getStudentID(),
                new SimpleDateFormat("yyyy-MM-dd").format(attendance.getattendancedate()),
                attendance.getStatus()
            });
        }

        // Set the model and refresh the table
        attendanceTable.setModel(model);

        // Customize table appearance
        attendanceTable.setForeground(Color.BLACK);
        attendanceTable.setBackground(Color.WHITE);
        attendanceTable.setGridColor(Color.GRAY);
        attendanceTable.getTableHeader().setBackground(Color.LIGHT_GRAY);
        attendanceTable.getTableHeader().setForeground(Color.BLACK);
        attendanceTable.revalidate();
        attendanceTable.repaint();
    }
}
