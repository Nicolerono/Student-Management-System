/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentDAO {
    private Connection connection;

    // Constructor to initialize the database connection
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

    // Method to add a new student to the database
    public boolean addStudent(Student student) {
    String sql = "INSERT INTO students (Student_ID, Full_Name, Age, Email, Course) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, student.getStudentID());
        stmt.setString(2, student.getFullName());
        stmt.setInt(3, student.getAge());
        stmt.setString(4, student.getEmail());
        stmt.setString(5, student.getCourse());
        return stmt.executeUpdate() > 0; // Returns true if insertion is successful
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Return false on exception
    }
}

    // Method to retrieve a student by ID
    public Student getStudentById(String studentID) {
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
        return null; // Return null if student not found
    }

    // Method to retrieve all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                students.add(new Student(
                    resultSet.getInt("studentID"),
                    resultSet.getString("fullName"),
                    resultSet.getInt("age"),
                    resultSet.getString("email"),
                    resultSet.getString("course")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Method to update a student's information
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

    // Method to delete a student from the database
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

    // Close the database connection
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
