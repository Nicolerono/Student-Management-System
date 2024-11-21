/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Student;
import model.StudentRecord;
import model.Attendance;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentDAO {
    private Connection connection;

   
    public StudentDAO() {
        String url = "jdbc:mysql://localhost:3306/student_management?useSSL=false&serverTimezone=UTC";
String user = "root";
String password = "";

try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Database connection established successfully.");
    } catch (ClassNotFoundException e) {
        System.out.println("MySQL JDBC Driver not found.");
        e.printStackTrace();
    } catch (SQLException e) {
        System.out.println("Connection to the database failed.");
        e.printStackTrace();
    }
}

   
    
    public boolean addStudent(Student student) {
    String sql = "INSERT INTO students (Student_ID, Full_Name, Age, Email, Course) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, student.getStudentID());
        stmt.setString(2, student.getFullName());
        stmt.setInt(3, student.getAge());
        stmt.setString(4, student.getEmail());
        stmt.setString(5, student.getCourse());
        return stmt.executeUpdate() > 0; 
    } catch (SQLException e) {
        e.printStackTrace();
        return false; 
    }
}

  
    public Student getStudentById(int studentID) {
        String query = "SELECT * FROM students WHERE studentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Student(
                    resultSet.getInt("studentID"),
                    resultSet.getString("fullName"),
                    resultSet.getInt("age"),
                    resultSet.getString("email"),
                    resultSet.getString("course")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

   
             
    public List<StudentRecord> getAllStudentRecords() {
        List<StudentRecord> records = new ArrayList<>();
        String query = "SELECT * FROM student_records";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                records.add(new StudentRecord(
                    resultSet.getInt("record_id"),
                    resultSet.getInt("student_id"),
                    resultSet.getString("subject"),
                   resultSet.getString("grade"),
                    resultSet.getString("semester"),
                    resultSet.getInt("year")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }
    
    public List<StudentRecord> getStudentRecordsByid(int studentID) {
    List<StudentRecord> records = new ArrayList<>();
    
    String query = "SELECT * FROM student_records WHERE student_id = ?";
     try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, studentID);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            StudentRecord record = new StudentRecord(
                resultSet.getInt("record_id"),
                resultSet.getInt("student_id"),
                resultSet.getString("subject"),
                resultSet.getString("grade"),
                resultSet.getString("semester"),
                resultSet.getInt("year")
            );
            records.add(record);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return records;
}
    
    public List<Attendance> getAttendanceRecordsByStudentId(int studentID) {
    List<Attendance> attendanceList = new ArrayList<>();
    String query = "SELECT * FROM attendance WHERE student_ID = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, studentID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Attendance attendance = new Attendance(
                resultSet.getInt("student_ID"),
                resultSet.getDate("attendance_date"),
                resultSet.getString("status")
            );
            attendanceList.add(attendance);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return attendanceList;
}


     
    public List<Attendance> getAllAttendanceRecords()  {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT attendance_ID, student_ID, attendance_date, status FROM attendance";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String attendanceId = resultSet.getString("attendance_ID");
                int studentID = resultSet.getInt("student_ID");
                Date attendancedate = resultSet.getDate("attendance_date");
                String status = resultSet.getString("status");

                Attendance attendance = new Attendance(studentID, attendancedate, status);
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to fetch attendance records.");
        }

        return attendanceList;
    }

   
    public boolean updateStudent(Student student) {
        String query = "UPDATE students SET Full_Name = ?, age = ?, email = ?, course = ? WHERE student_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getFullName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getCourse());
            statement.setInt(5, student.getStudentID());
           int rowsUpdated = statement.executeUpdate();
    return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
             return false;
        }
    }

  
    public boolean deleteStudent(int studentID) {
        String query = "DELETE FROM students WHERE Student_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentID);
            int rowsDeleted = statement.executeUpdate();
    return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
     public List<Student> getStudentRecords() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students"; 
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                    rs.getInt("Student_ID"),
                    rs.getString("Full_Name"),
                    rs.getInt("Age"),
                    rs.getString("Email"),
                    rs.getString("Course")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
     
      public Connection getConnection() {
        return connection;
    }


    
 
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   

   

    
}
