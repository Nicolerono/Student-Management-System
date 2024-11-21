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
    private String role; 
    private JTable attendanceTable;
    private StudentDAO studentDAO;

    
    public AttendanceView( int studentID, String role, StudentDAO studentDAO) {
        this.role = role;
        this.studentID = studentID;
        this.studentDAO = studentDAO;

        initUI();
        loadAttendanceRecords(); 
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Attendance Records", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 16));
        add(header, BorderLayout.NORTH);

       
        attendanceTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadAttendanceRecords() {
        List<Attendance> attendanceRecords;

        
        if (role.equalsIgnoreCase("admin")) {
            attendanceRecords = studentDAO.getAllAttendanceRecords(); 
        } else {
            attendanceRecords = studentDAO.getAttendanceRecordsByStudentId(studentID); 
        }

        
        String[] columnNames = {"Student ID", "Attendance Date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Attendance attendance : attendanceRecords) {
            model.addRow(new Object[]{
                attendance.getStudentID(),
                new SimpleDateFormat("yyyy-MM-dd").format(attendance.getattendancedate()),
                attendance.getStatus()
            });
        }

  
        attendanceTable.setModel(model);

       
        attendanceTable.setForeground(Color.BLACK);
        attendanceTable.setBackground(Color.WHITE);
        attendanceTable.setGridColor(Color.GRAY);
        attendanceTable.getTableHeader().setBackground(Color.LIGHT_GRAY);
        attendanceTable.getTableHeader().setForeground(Color.BLACK);
        attendanceTable.revalidate();
        attendanceTable.repaint();
    }
}
