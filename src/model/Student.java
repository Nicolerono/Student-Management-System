/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nicole
 */
public class Student {
    private int studentID;
    private String fullName;
    private int age;
    private String email;
    private String course;

    // Constructor
    public Student(int studentID, String fullName, int age, String email, String course) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.course = course;
    }

    // Getters and Setters
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // toString method to display the student's information
    @Override
    public String toString() {
        return "Student ID: " + studentID + "\n" +
               "Full Name: " + fullName + "\n" +
               "Age: " + age + "\n" +
               "Email: " + email + "\n" +
               "Course: " + course;
    }
}
