package view;

import dao.StudentDAO;
import model.StudentRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentRecordsView extends JPanel {
    private int studentID;
    private String role; // Role of the user (admin or student)
    private StudentDAO studentDAO;

    private JTable studentRecordsTable;

    // Constructor for role-based view
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

        // Create a table for student records
        studentRecordsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(studentRecordsTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadStudentRecords() {
        List<StudentRecord> records;

        // Fetch student records based on the role
        if (role.equalsIgnoreCase("admin")) {
            records = studentDAO.getAllStudentRecords(); // Admin gets all records
        } else {
            records = studentDAO.getStudentRecordsByid(studentID); // Student gets their own records
        }

        // Populate the table with records
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

        // Customize table appearance
        studentRecordsTable.setRowHeight(25);
        studentRecordsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        studentRecordsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        studentRecordsTable.setGridColor(Color.GRAY);
        studentRecordsTable.setForeground(Color.BLACK);
        studentRecordsTable.setBackground(Color.WHITE);
    }
}
