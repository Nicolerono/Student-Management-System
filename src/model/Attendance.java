/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nicole
 */
public class Attendance {
    private String attendanceId;
    private String studentId;
    private String date; // Use Date type if you want to handle dates more precisely
    private boolean isPresent;

    public Attendance(String attendanceId, String studentId, String date, boolean isPresent) {
        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.date = date;
        this.isPresent = isPresent;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceId='" + attendanceId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", date='" + date + '\'' +
                ", isPresent=" + isPresent +
                '}';
    }
}