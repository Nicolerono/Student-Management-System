/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Nicole
 */
import model.Grade;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    private Connection conn;

    public GradeDAO(Connection conn) {
        this.conn = conn;
    }

    public void addGrade(Grade grade) throws SQLException {
        String query = "INSERT INTO grade (gradeId, studentId, courseId, grade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, grade.getGradeId());
            stmt.setString(2, grade.getStudentId());
            stmt.setString(3, grade.getCourseId());
            stmt.setString(4, grade.getGrade());
            stmt.executeUpdate();
        }
    }

    public Grade getGradeById(String gradeId) throws SQLException {
        String query = "SELECT * FROM grade WHERE gradeId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, gradeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Grade(
                    rs.getString("gradeId"),
                    rs.getString("studentId"),
                    rs.getString("courseId"),
                    rs.getString("grade")
                );
            }
            return null;
        }
    }

    public List<Grade> getAllGrades() throws SQLException {
        String query = "SELECT * FROM grade";
        List<Grade> grades = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                grades.add(new Grade(
                    rs.getString("gradeId"),
                    rs.getString("studentId"),
                    rs.getString("courseId"),
                    rs.getString("grade")
                ));
            }
        }
        return grades;
    }

    public void updateGrade(Grade grade) throws SQLException {
        String query = "UPDATE grade SET studentId = ?, courseId = ?, grade = ? WHERE gradeId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, grade.getStudentId());
            stmt.setString(2, grade.getCourseId());
            stmt.setString(3, grade.getGrade());
            stmt.setString(4, grade.getGradeId());
            stmt.executeUpdate();
        }
    }

    public void deleteGrade(String gradeId) throws SQLException {
        String query = "DELETE FROM grade WHERE gradeId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, gradeId);
            stmt.executeUpdate();
        }
    }
}
