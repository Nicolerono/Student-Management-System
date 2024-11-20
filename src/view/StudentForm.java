/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.Student;
import dao.StudentDAO;
import model.AdminAccess;
import dao.AdminAccessDAO;
import dao.StudentAccessDAO;
import view.LoginView;


import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.net.URL;
import javax.swing.JTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import model.Attendance;
import java.util.List;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Nicole
 */
public class StudentForm extends javax.swing.JFrame {
    private int studentID;
    private String role;
     private Image backgroundImage;
    private StudentDAO studentDAO;
    private JTable attendanceTable;
private JTabbedPane tabbedPane;

    
    

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
    public StudentForm(int studentID, String role, int selectedTabIndex)  { 
        
        
        if (studentID <= 0) {
        throw new IllegalArgumentException("Invalid user ID");
    }
    if (!"admin".equals(role) && !"student".equals(role)) {
        throw new IllegalArgumentException("Invalid role: " + role);
    }
        
          // Initialize roles
      this.studentID = studentID;
        this.role = role;
        
         
         
    
     
     


        try {
    URL imageURL = getClass().getClassLoader().getResource("model/images/background.jpg");
    if (imageURL != null) {
        backgroundImage = ImageIO.read(imageURL);
    } else {
        System.err.println("Image not found in the resources!");
    }
} catch (IOException e) {
    e.printStackTrace();
    
    
}
    
    
   
        
          BackgroundPanel mainPanel = new BackgroundPanel();
        setContentPane(mainPanel); 
        
        // Display login dialog
     
          
        
     studentDAO = new StudentDAO(); 
     
        setTitle("Student Management System");
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         setLayout(new BorderLayout()); 
         
         
         
         
         

        
        
        JLabel headerLabel = new JLabel("Student Management System", JLabel.CENTER);
    headerLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
    headerLabel.setOpaque(true);
    headerLabel.setBackground(new Color(0xE1D6CE)); // Old Lace background
    headerLabel.setForeground(new Color(0xb27f58)); // Deer text color
    headerLabel.setPreferredSize(new Dimension(600, 50));
    add(headerLabel, BorderLayout.NORTH);

        // Initialize and set up components
        initializeComponents();
        
        if ("student".equals(role)) {
        addButton.setEnabled(false);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }
        
       statusBar = new JLabel("Status: Ready", JLabel.CENTER);
        statusBar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        statusBar.setOpaque(true);
        statusBar.setBackground(new Color(0xE1D6CE)); // Old Lace background
        statusBar.setForeground(new Color(0x333333)); // Darker text for readability
        statusBar.setPreferredSize(new Dimension(600, 30));
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
        
        
    }

   
    
     private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Draw the image, scaled to fit the panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
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

       
Color peachPuff = (new Color(0xFFDAB9));
Color darkBrown = (new Color(75, 46, 46));        

        
JTabbedPane tabbedPane = new JTabbedPane();

tabbedPane.setBackground(peachPuff);
tabbedPane.setForeground(new Color(0xBA8759));

UIManager.put("TabbedPane.selectedBackground", peachPuff);
UIManager.put("TabbedPane.selectedForeground", darkBrown); // Text for selected tab
UIManager.put("TabbedPane.unselectedBackground", new Color(0xBA8759)); // Deer color
UIManager.put("TabbedPane.unselectedForeground", Color.WHITE); // Light text for unselected tabs
Font tabFont = new Font("Segoe UI", Font.BOLD, 14);
tabbedPane.setFont(tabFont);
  tabbedPane.setBackground(peachPuff);
        tabbedPane.setForeground(darkBrown);
        
   

// ScrollPane border


     JPanel studentInfoPanel = new RoundedPanel(backgroundColor, 20, 10);
     
    studentInfoPanel.setPreferredSize(new Dimension(200, 150));
studentInfoPanel.setLayout(new GridBagLayout());
GridBagConstraints gbc = new GridBagConstraints();
gbc.insets = new Insets(8, 8, 8, 8); // Padding between components
gbc.fill = GridBagConstraints.HORIZONTAL; 


studentInfoPanel.revalidate();
studentInfoPanel.repaint();


    
     addLabelAndField(studentInfoPanel, gbc, "Student ID:", studentIDField, labelFont, textColor, 0);
 
addLabelAndField(studentInfoPanel, gbc, "Full Name:", fullNameField, labelFont, textColor, 1);
addLabelAndField(studentInfoPanel, gbc, "Age:", ageField, labelFont, textColor, 2);
addLabelAndField(studentInfoPanel, gbc, "Email:", emailField, labelFont, textColor, 3);
addLabelAndField(studentInfoPanel, gbc, "Course:", courseField, labelFont, textColor, 4);
    
    
   JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
buttonPanel.setBackground(backgroundColor);
buttonPanel.add(addButton);
buttonPanel.add(updateButton);
buttonPanel.add(deleteButton);

    gbc.gridx = 0;
gbc.gridy = 5;
gbc.gridwidth = 2;
studentInfoPanel.add(buttonPanel, gbc);

  
  
   
   
    pack();
        setLocationRelativeTo(null);

      

 
studentInfoPanel.setForeground(darkBrown);
tabbedPane.addTab("Student Information", studentInfoPanel);

StudentDAO studentDAO = new StudentDAO();


AttendanceView adminView = new AttendanceView(0, "admin", new StudentDAO());
AttendanceView studentView = new AttendanceView(studentID, "student", new StudentDAO());

tabbedPane.addTab("Attendance", adminView);


StudentRecordsView recordsView = new StudentRecordsView("admin", 0,studentDAO);


        tabbedPane.addTab("Student Records", recordsView);


 

    // Create the Attendance JPanel
JPanel attendancePanel = new JPanel();
attendancePanel.setLayout(new BorderLayout()); // Use BorderLayout for better organization

// Create the JTable for displaying attendance records
JTable attendanceTable = new JTable();
attendanceTable.setFillsViewportHeight(true); // Ensure the table expands to fill the available space

// Add the JTable to a JScrollPane for scrollable functionality
JScrollPane scrollPane = new JScrollPane(attendanceTable);
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

// Add the JScrollPane (with the JTable) to the attendancePanel
attendancePanel.add(scrollPane, BorderLayout.CENTER);

// Optional: Add a heading or label above the table
JLabel headingLabel = new JLabel("Attendance Records");
headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
headingLabel.setFont(new Font("Arial", Font.BOLD, 16));
headingLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
attendancePanel.add(headingLabel, BorderLayout.NORTH);

// Styling and final adjustments
attendanceTable.setForeground(Color.BLACK);
attendanceTable.setBackground(Color.WHITE);
attendanceTable.setGridColor(Color.LIGHT_GRAY);
attendanceTable.getTableHeader().setBackground(Color.DARK_GRAY);
attendanceTable.getTableHeader().setForeground(Color.WHITE);

// Add the attendancePanel to the main container or tab
 List<Attendance> attendanceList = studentDAO.getAllAttendanceRecords();

  






     

tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        g.setColor(isSelected ? peachPuff.darker() : peachPuff); // Darker Dusty Rose for selected tab
        g.fillRect(x, y, w, h);
    }
});

        
      
      
getContentPane().add(tabbedPane, BorderLayout.CENTER);

 
    int selectedTabIndex = 1;
    if (selectedTabIndex >= 0 && selectedTabIndex < tabbedPane.getTabCount()) {
            tabbedPane.setSelectedIndex(selectedTabIndex);
        }




             
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

private void clearFields() {
    studentIDField.setText("");
    fullNameField.setText("");
    ageField.setText("");
    emailField.setText("");
    courseField.setText("");
}
    
private boolean isStudentIDValid() {
    try {
        int studentID = Integer.parseInt(studentIDField.getText());
        if (studentID > 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Student ID must be a positive integer.");
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter a valid number for Student ID.");
        return false;
    }
}

private boolean isAgeValid() {
    try {
        int age = Integer.parseInt(ageField.getText());
        if (age >= 5 && age <= 120) { // Example age range
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "Age must be between 5 and 120.");
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter a valid number for Age.");
        return false;
    }
}

private boolean isEmailValid() {
    String email = emailField.getText();
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    if (email.matches(emailRegex)) {
        return true;
    } else {
        JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
        return false;
    }
}

private boolean isFullNameValid() {
    String fullName = fullNameField.getText();
    if (fullName != null && !fullName.trim().isEmpty()) {
        return true;
    } else {
        JOptionPane.showMessageDialog(this, "Full Name cannot be empty.");
        return false;
    }
}

private boolean isCourseValid() {
    String course = courseField.getText();
    if (course != null && !course.trim().isEmpty()) {
        return true;
    } else {
        JOptionPane.showMessageDialog(this, "Course cannot be empty.");
        return false;
    }
}

 
    
    
   






    private void addStudent() {
        if (!"admin".equals(role)) {
            JOptionPane.showMessageDialog(this, "Access denied: Admin privileges required to add students.");
            return;
        }
        
        if (!isStudentIDValid() || !isFullNameValid() || !isAgeValid() || !isEmailValid() || !isCourseValid()) {
        statusBar.setText("Status: Validation Failed");
        statusBar.setForeground(Color.RED);
        return;
    }
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
        
        
         if (!"admin".equals(role)) {
            JOptionPane.showMessageDialog(this, "Access denied: Admin privileges required to update students.");
            return;
        }
         
         if (!isStudentIDValid() || !isFullNameValid() || !isAgeValid() || !isEmailValid() || !isCourseValid()) {
        statusBar.setText("Status: Validation Failed");
        statusBar.setForeground(Color.RED);
        return;
    }

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
         
          if (!"admin".equals(role)) {
            JOptionPane.showMessageDialog(this, "Access denied: Admin privileges required to delete students.");
            return;
        }
          
          if (!isStudentIDValid()) {
        statusBar.setText("Status: Validation Failed");
        statusBar.setForeground(Color.RED);
        return;
    }

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
     
     public static void main(String[] args) throws SQLException {
         
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "");
    AdminAccessDAO adminAccessDAO = new AdminAccessDAO(connection);
    StudentAccessDAO studentAccessDAO = new StudentAccessDAO(connection);

    // Display LoginView
    new LoginView(adminAccessDAO, studentAccessDAO).setVisible(true);

    // Launch the StudentForm
    SwingUtilities.invokeLater(() -> {
       LoginView loginView = new LoginView(adminAccessDAO, studentAccessDAO);
            loginView.setVisible(true);
    });


    
     
   
    
   
        try {
        // Set Nimbus Look and Feel
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
  
        }
   
}



        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
     
        
         

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

