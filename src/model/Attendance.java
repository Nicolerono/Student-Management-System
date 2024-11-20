/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Nicole
 */
public class Attendance {
private String attendanceId;
    private int studentID;
    private Date attendancedate; // Use Date type if you want to handle dates more precisely
    private String status;

    public Attendance(int studentID, Date attendancedate, String status) {
         this.studentID = studentID;
        this.attendancedate = attendancedate;
        this.status = status;
    }
    
    
    
   public String getAttendanceId() { return attendanceId; }
    public int getStudentID() { return studentID; }
    public Date getDate() { return attendancedate; }
    public String status() { return status; }

    public Attendance(String attendanceId, int studentID, Date attendancedate, String status) {
          this.attendanceId = attendanceId;
        this.studentID = studentID;
        this.attendancedate = attendancedate;
        this.status = status;
    }

   

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    
public Date getattendancedate() {
        return attendancedate;
    }
   

   public void setDate(Date date) {
        this.attendancedate = date;
    }

    
    public String getStatus() {
        return status;
    }


   public void setStatus(String status) {
        this.status = status;
    }
}
   

    