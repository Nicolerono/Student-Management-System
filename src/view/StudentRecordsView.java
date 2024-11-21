package view;

import dao.StudentDAO;
import model.StudentRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentRecordsView extends JPanel {
    private int studentID;
    private String role; 
    private StudentDAO studentDAO;

    private JTable studentRecordsTable;

    
    public StudentRecordsView(String role, int studentID, StudentDAO studentDAO) {
        this.role = role;
        this.studentID = studentID;
        this.studentDAO = studentDAO;

        initializeComponents();
        loadStudentRecords();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Student Records", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

       
        studentRecordsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(studentRecordsTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadStudentRecords() {
        List<StudentRecord> records;

      
        if (role.equalsIgnoreCase("admin")) {
            records = studentDAO.getAllStudentRecords();
        } else {
            records = studentDAO.getStudentRecordsByid(studentID); 
        }

        
        DefaultTableModel tableModel = new DefaultTableModel(
                new String[]{"Record ID", "Student ID", "Subject", "Grade", "Semester", "Year"}, 0
        );

        for (StudentRecord record : records) {
            tableModel.addRow(new Object[]{
                    record.getRecordId(),
                    record.getStudentId(),
                    record.getSubject(),
                    record.getGrade(),
                    record.getSemester(),
                    record.getYear()
            });
        }

        studentRecordsTable.setModel(tableModel);

      
        studentRecordsTable.setRowHeight(25);
        studentRecordsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        studentRecordsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        studentRecordsTable.setGridColor(Color.GRAY);
        studentRecordsTable.setForeground(Color.BLACK);
        studentRecordsTable.setBackground(Color.WHITE);
    }
}
