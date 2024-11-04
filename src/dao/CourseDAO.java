/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Nicole
 */
import model.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private Connection conn;

    public CourseDAO(Connection conn) {
        this.conn = conn;
    }

    public void addCourse(Course course) throws SQLException {
        String query = "INSERT INTO course (courseId, courseName, credits) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, course.getCourseId());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getCredits());
            stmt.executeUpdate();
        }
    }

    public Course getCourseById(String courseId) throws SQLException {
        String query = "SELECT * FROM course WHERE courseId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Course(
                    rs.getString("courseId"),
                    rs.getString("courseName"),
                    rs.getInt("credits")
                );
            }
            return null;
        }
    }

    public List<Course> getAllCourses() throws SQLException {
        String query = "SELECT * FROM course";
        List<Course> courses = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                courses.add(new Course(
                    rs.getString("courseId"),
                    rs.getString("courseName"),
                    rs.getInt("credits")
                ));
            }
        }
        return courses;
    }

    public void updateCourse(Course course) throws SQLException {
        String query = "UPDATE course SET courseName = ?, credits = ? WHERE courseId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, course.getCourseName());
            stmt.setInt(2, course.getCredits());
            stmt.setString(3, course.getCourseId());
            stmt.executeUpdate();
        }
    }

    public void deleteCourse(String courseId) throws SQLException {
        String query = "DELETE FROM course WHERE courseId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, courseId);
            stmt.executeUpdate();
        }
    }
}
