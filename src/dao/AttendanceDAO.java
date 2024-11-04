/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Nicole
 */
import model.Attendance;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    private Connection conn;

    public AttendanceDAO(Connection conn) {
        this.conn = conn;
    }

    public void addAttendance(Attendance attendance) throws SQLException {
        String query = "INSERT INTO attendance (attendanceId, studentId, date, isPresent) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, attendance.getAttendanceId());
            stmt.setString(2, attendance.getStudentId());
            stmt.setString(3, attendance.getDate());
            stmt.setBoolean(4, attendance.isPresent());
            stmt.executeUpdate();
        }
    }

    public Attendance getAttendanceById(String attendanceId) throws SQLException {
        String query = "SELECT * FROM attendance WHERE attendanceId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, attendanceId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Attendance(
                    rs.getString("attendanceId"),
                    rs.getString("studentId"),
                    rs.getString("date"),
                    rs.getBoolean("isPresent")
                );
            }
            return null;
        }
    }

    public List<Attendance> getAllAttendances() throws SQLException {
        String query = "SELECT * FROM attendance";
        List<Attendance> attendances = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                attendances.add(new Attendance(
                    rs.getString("attendanceId"),
                    rs.getString("studentId"),
                    rs.getString("date"),
                    rs.getBoolean("isPresent")
                ));
            }
        }
        return attendances;
    }

    public void updateAttendance(Attendance attendance) throws SQLException {
        String query = "UPDATE attendance SET studentId = ?, date = ?, isPresent = ? WHERE attendanceId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, attendance.getStudentId());
            stmt.setString(2, attendance.getDate());
            stmt.setBoolean(3, attendance.isPresent());
            stmt.setString(4, attendance.getAttendanceId());
            stmt.executeUpdate();
        }
    }

    public void deleteAttendance(String attendanceId) throws SQLException {
        String query = "DELETE FROM attendance WHERE attendanceId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, attendanceId);
            stmt.executeUpdate();
        }
    }
}

