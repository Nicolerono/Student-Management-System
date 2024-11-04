/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.Student;
import dao.StudentDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




/**
 *
 * @author Nicole
 */
public class StudentForm extends javax.swing.JFrame {
    private StudentDAO studentDAO;

   private JTextField studentIDField;
    private JTextField ageField;
    private JTextField emailField;
    private JTextField courseField;
    private JTextField fullNameField;
     private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
private JLabel statusBar;

    /**
     * Creates new form StudentForrm
     */
    public StudentForm()  {      
     studentDAO = new StudentDAO(); 
        setTitle("Student Information Form");
        setSize(400, 500);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         setLayout(new BorderLayout()); 
        
        
        JLabel headerLabel = new JLabel("Student Information System", JLabel.CENTER);
    headerLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
    headerLabel.setOpaque(true);
    headerLabel.setBackground(new Color(0xE1D6CE)); // Old Lace background
    headerLabel.setForeground(new Color(0xb27f58)); // Deer text color
    headerLabel.setPreferredSize(new Dimension(400, 50));
    add(headerLabel, BorderLayout.NORTH);

        // Initialize and set up components
        initializeComponents();
       statusBar = new JLabel("Status: Ready", JLabel.CENTER);
        statusBar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        statusBar.setOpaque(true);
        statusBar.setBackground(new Color(0xE1D6CE)); // Old Lace background
        statusBar.setForeground(new Color(0x333333)); // Darker text for readability
        statusBar.setPreferredSize(new Dimension(400, 30));
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
        
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
     

    
    private void initializeComponents() {
        
       Color backgroundColor = new Color(0xfdf5e6);
        Color buttonColor = new Color(0xba8759);
        Color textColor = Color.decode("#333333");
        Font labelFont = new Font("SansSerif", Font.BOLD, 20);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);

     getContentPane().setBackground(backgroundColor);
        // Initialize fields
        
        studentIDField = createStyledTextField();
    ageField = createStyledTextField();
    emailField = createStyledTextField();
    courseField = createStyledTextField();
    fullNameField = createStyledTextField();
    
     addButton = createStyledButton("Add Student", buttonColor, textColor, buttonFont);
        updateButton = createStyledButton("Update Student", buttonColor, textColor, buttonFont);
        deleteButton = createStyledButton("Delete Student", buttonColor, textColor, buttonFont);

       


        // Create a panel with a grid layout
       RoundedPanel mainPanel = new RoundedPanel(backgroundColor, 20, 10);
       mainPanel.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
  gbc.insets = new Insets(8, 8, 8, 8); // Add padding between components
    gbc.fill = GridBagConstraints.HORIZONTAL;
    
     addLabelAndField(mainPanel, gbc, "Student ID:", studentIDField, labelFont, textColor, 0);
        addLabelAndField(mainPanel, gbc, "Full Name:", fullNameField, labelFont, textColor, 1);
        addLabelAndField(mainPanel, gbc, "Age:", ageField, labelFont, textColor, 2);
        addLabelAndField(mainPanel, gbc, "Email:", emailField, labelFont, textColor, 3);
        addLabelAndField(mainPanel, gbc, "Course:", courseField, labelFont, textColor, 4);
    
    
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    buttonPanel.setBackground(backgroundColor);
    buttonPanel.add(addButton);
    buttonPanel.add(updateButton);
    buttonPanel.add(deleteButton);

    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.gridwidth = 2;
    mainPanel.add(buttonPanel, gbc);


        // Add labels and fields to the panel
      

        
             getContentPane().add(mainPanel, BorderLayout.CENTER);
             
             addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
    }
    
    
     private JTextField createStyledTextField() {
        JTextField textField = new JTextField(15);
        textField.setBackground(new Color(0xfdf5e6));
        textField.setForeground(new Color(0xba8759));
        textField.setCaretColor(Color.BLACK);
        return textField;
    }
             
            private JButton createStyledButton(String text, Color backgroundColor, Color textColor, Font font) {
        JButton button = new JButton(text);
        button.setBackground (new Color(0xba8759));
        button.setForeground(new Color(0xfdf5e6));
        button.setFont(font);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

     button.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setBackground(new Color(0xD2A679)); 
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(new Color(0xba8759)); // Original color
        }
    });
    return button;
}
          
private void addLabelAndField(JPanel panel, GridBagConstraints gbc, String labelText, JTextField field, Font font, Color color, int yPosition) {
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        label.setForeground(new Color(0xba8759));
        gbc.gridx = 0;
        gbc.gridy = yPosition;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }
             
             
    /**
     * 
     * @param args the command line arguments
     */
    
    private void addStudent() {
        
           System.out.println("Add button clicked!");
    
        try {
            int Student_ID = Integer.parseInt(studentIDField.getText());
            String Full_Name = fullNameField.getText();
            int Age = Integer.parseInt(ageField.getText());
            String Email = emailField.getText();
            String Course = courseField.getText();

             Student student = new Student(Student_ID, Full_Name, Age, Email, Course);
        boolean success = studentDAO.addStudent(student);

             if (success) {
            JOptionPane.showMessageDialog(this, "Student added successfully!");
            statusBar.setText("Status: Student Added Successfully");
            statusBar.setForeground(Color.GREEN);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add student.");
            statusBar.setText("Status: Failed to Add Student");
            statusBar.setForeground(Color.RED);
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Student ID and Age.");
        statusBar.setText("Status: Invalid Input - Check Student ID and Age");
        statusBar.setForeground(Color.RED);
    
    }

          this.revalidate();
    this.repaint();
    }

    private void updateStudent() {
        try {
            int studentID = Integer.parseInt(studentIDField.getText());
            String FullName = fullNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String email = emailField.getText();
            String course = courseField.getText();

            Student student = new Student(studentID, FullName, age, email, course);
            boolean result = studentDAO.updateStudent(student);

            if (result) {
            JOptionPane.showMessageDialog(this, "Student updated successfully!");
            statusBar.setText("Status: Student Updated Successfully");
            statusBar.setForeground(Color.GREEN);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update student.");
            statusBar.setText("Status: Failed to Update Student");
            statusBar.setForeground(Color.RED);
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter valid numeric values for Student ID and Age.");
        statusBar.setText("Status: Invalid Input - Check Student ID and Age");
        statusBar.setForeground(Color.RED);
    
    }
    }

     private void deleteStudent() {
        try {
            int studentID = Integer.parseInt(studentIDField.getText());
            boolean success = studentDAO.deleteStudent(studentID);

             if (success) {
            JOptionPane.showMessageDialog(this, "Student deleted successfully!");
            // Optionally clear the form fields after deletion
            studentIDField.setText("");
            fullNameField.setText("");
            ageField.setText("");
            emailField.setText("");
            courseField.setText("");

            statusBar.setText("Status: Student Deleted Successfully");
            statusBar.setForeground(Color.GREEN);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete student.");
            statusBar.setText("Status: Failed to Delete Student");
            statusBar.setForeground(Color.RED);
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter a valid numeric Student ID.");
        statusBar.setText("Status: Invalid Input - Check Student ID");
        statusBar.setForeground(Color.RED);
    }
     }
    

    
    public static void main(String[] args) {
            SwingUtilities.invokeLater(StudentForm::new);
    }
}


        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
     
        
         

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

