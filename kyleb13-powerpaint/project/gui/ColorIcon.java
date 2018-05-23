/*
 * TCSS 305, Spring 2017 
 */
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;

/**
 * Creates a solid color icon for the color buttons.
 * @author Kyle Beveridge
 * @version 5/16/18.
 */
public class ColorIcon implements Icon {
    /**
     * Height of the icon.
     */
    private static final int HEIGHT = 15;
    
    /**
     * Width of icon.
     */
    private static final int WIDTH = 15;
    
    /**
     * Color of the icon.
     */
    private Color myColor = Color.WHITE;

    /**
     * Initializes the icon to a certian color.
     * @param c1 the color.
     */
    public ColorIcon(final Color c1) {
        myColor = c1;
    }
    
    /**
     * Set the Icon to a new color.
     * @param c1 the new color.
     */
    public void setColor(final Color c1) {
        myColor = c1;
    }
    
    @Override
    public void paintIcon(final Component arg0, 
        final Graphics arg1, final int arg2, final int arg3) {
        
        final Graphics2D g2 = (Graphics2D) arg1.create();
        g2.setColor(myColor);
        g2.fillRect(2 * 2, 2 * 2, WIDTH, HEIGHT);
        g2.dispose();
    }


    @Override
    public int getIconHeight() {
        return HEIGHT;
    }


    @Override
    public int getIconWidth() {
        return WIDTH;
    }
}
