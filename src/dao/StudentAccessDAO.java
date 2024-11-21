/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Nicole
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentAccessDAO {
    private Connection connection;

    public StudentAccessDAO(Connection connection) {
        this.connection = connection;
    }

    

    
    public boolean validateStudent(String studentId, String password) {
        String query = "SELECT * FROM student_access WHERE student_id = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studentId);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

  
    public boolean addStudentLogin(int studentId, String password) {
        String query = "INSERT INTO student_access (student_id, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            statement.setString(2, password);
            return statement.executeUpdate() > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean updateStudentPassword(int studentId, String newPassword) {
        String query = "UPDATE student_access SET password = ? WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newPassword);
            statement.setInt(2, studentId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public boolean deleteStudentLogin(int studentId) {
        String query = "DELETE FROM student_access WHERE student_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
 



}

