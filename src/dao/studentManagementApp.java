/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Nicole
 */
import model.Student;
import java.util.Scanner;

public class studentManagementApp {

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Get Student by ID");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a new student
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Full Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Course: ");
                    String course = scanner.nextLine();

                    Student newStudent = new Student(id, name, age, email, course);
                    studentDAO.addStudent(newStudent);
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    // Get a student by ID
                    System.out.print("Enter Student ID to retrieve: ");
                    String retrieveID = scanner.nextLine();
                    Student student = studentDAO.getStudentById(retrieveID);
                    if (student != null) {
                        System.out.println("Student Details:\n" + student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    // Update a student
                    System.out.print("Enter Student ID to update: ");
                    String updateID = scanner.nextLine();
                    Student updateStudent = studentDAO.getStudentById(updateID);
                    if (updateStudent != null) {
                        System.out.print("Enter New Full Name (or press Enter to keep current): ");
                        String newName = scanner.nextLine();
                        if (!newName.isEmpty()) updateStudent.setFullName(newName);

                        System.out.print("Enter New Age (or 0 to keep current): ");
                        int newAge = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        if (newAge > 0) updateStudent.setAge(newAge);

                        System.out.print("Enter New Email (or press Enter to keep current): ");
                        String newEmail = scanner.nextLine();
                        if (!newEmail.isEmpty()) updateStudent.setEmail(newEmail);

                        System.out.print("Enter New Course (or press Enter to keep current): ");
                        String newCourse = scanner.nextLine();
                        if (!newCourse.isEmpty()) updateStudent.setCourse(newCourse);

                        studentDAO.updateStudent(updateStudent);
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    // Delete a student
                    System.out.print("Enter Student ID to delete: ");
                    String deleteID = scanner.nextLine();
                    studentDAO.deleteStudent(deleteID);
                    System.out.println("Student deleted successfully.");
                    break;

                case 5:
                    // Display all students
                    System.out.println("All Students:");
                    for (Student s : studentDAO.getAllStudents()) {
                        System.out.println(s);
                    }
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting...");
                    studentDAO.closeConnection();
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }
}

