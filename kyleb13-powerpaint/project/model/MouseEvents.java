/*
 * TCSS 305, Spring 2017
 * PowerPaint
 */
package model;

import gui.DrawPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Defines mouse events for draw panel.
 * @author Kyle Beveridge
 * @version 5/11/17
 */
public class MouseEvents extends MouseAdapter {
    /**
     * The Draw panel.
     */
    private final DrawPanel myPanel;
    
    /**
     * The primary color.
     */
    private Color myPrimary;
    
    /**
     * The secondary color.
     */
    private Color mySecondary;
    
    /**
     * The graphics of the panel.
     */
    private final Graphics2D myGraphics;
    
    /**
     * Construct mouse events.
     * @param thePanel the draw panel.
     * @param primary1 primary color.
     * @param secondary1 secondary color.
     */
    public MouseEvents(final DrawPanel thePanel, 
                       final Color primary1, final Color secondary1) {
        super();
        myPrimary = primary1;
        mySecondary = secondary1;
        myPanel = thePanel;
        myGraphics = (Graphics2D) myPanel.getGraphics();
    }
    
    /**
     * Set Primary color.
     * @param c1 the color.
     */
    public void setPrimary(final Color c1) {
        myPrimary = c1;
    }
    
    /**
     * Set secondary color.
     * @param c1 the color.
     */
    public void setSecondary(final Color c1) {
        mySecondary = c1;
    }
    
    @Override
    public void mousePressed(final MouseEvent e1) {
        if (e1.getButton() == MouseEvent.BUTTON3) {
            myPanel.setColor(mySecondary);
        } else {
            myPanel.setColor(myPrimary);
        }
        myPanel.setStart(e1.getPoint());
        myPanel.setEnd(e1.getPoint());
        myPanel.paintComponent(myGraphics);
    }
    
    @Override
    public void mouseReleased(final MouseEvent e1) {
        myPanel.finishDraw();
    }
    
    @Override
    public void mouseDragged(final MouseEvent e1) {
        myPanel.setEnd(e1.getPoint());
        myPanel.paintComponent(myGraphics);
    }
    
    @Override
    public void mouseEntered(final MouseEvent e1) {
        myPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }
    
    @Override
    public void mouseExited(final MouseEvent e1) {
        myPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
