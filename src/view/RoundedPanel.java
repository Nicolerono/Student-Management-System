/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Nicole
 */
public class RoundedPanel extends JPanel {
    private Color backgroundColor;
    private int cornerRadius;
    private int shadowSize;

    public RoundedPanel(Color backgroundColor, int cornerRadius, int shadowSize) {
        this.backgroundColor = backgroundColor;
        this.cornerRadius = cornerRadius;
        this.shadowSize = shadowSize;
        setOpaque(false); // Set to false for custom painting
}
    
 @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
         int margin = shadowSize / 2;
        
        // Draw shadow
        g2d.setColor(new Color(0, 0, 0, 50)); // Semi-transparent black for shadow
        g2d.fillRoundRect(shadowSize, shadowSize, getWidth() - shadowSize, getHeight() - shadowSize, cornerRadius, cornerRadius);
        
        // Draw panel background with rounded corners
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, getWidth() - shadowSize, getHeight() - shadowSize, cornerRadius, cornerRadius);
        
        g2d.dispose();
    }

@Override
    public Dimension getPreferredSize() {
          int margin = shadowSize / 2;
        return new Dimension(super.getPreferredSize().width + shadowSize, super.getPreferredSize().height + shadowSize);
    }
}
