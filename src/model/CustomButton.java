package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class CustomButton extends JButton {   
    Color color1, color2;   

    public CustomButton(String text, Color color1, Color color2) {   
        super(text);   
        this.color1 = color1;   
        this.color2 = color2;   
        setOpaque(false);   
        setSize(new Dimension(450, 350));
        setForeground(Color.white);
        setText(text);
        setContentAreaFilled(false);
    }   

    
    protected void paintComponent(Graphics g) {    
        super.paintComponent(g);
        int width = getWidth();   
        int height = getHeight();   

        GradientPaint paint = new GradientPaint(0, 0, color1, width, height,
                color2, true);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);   
        Paint oldPaint = g2d.getPaint();
        g2d.setPaint(paint);
        g2d.fillRect(0, 0, width, height);
        g2d.drawString("Button 1", getWidth()/2, 10);
        //g2d.setPaint(oldPaint);
    }   
}  