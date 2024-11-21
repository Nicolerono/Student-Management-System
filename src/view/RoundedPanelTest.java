/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Nicole
 */
public class RoundedPanelTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("RoundedPanel Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new BorderLayout());
            Color backgroundColor = null;

        
            RoundedPanel roundedPanel = new RoundedPanel(backgroundColor, 20, 10);
            roundedPanel.setPreferredSize(new Dimension(100, 100)); 
frame.setLayout(new FlowLayout());
            frame.add(roundedPanel, BorderLayout.CENTER);
             frame.pack();

            frame.setLocationRelativeTo(null); 
            frame.setVisible(true);
        });
    }
    
}
