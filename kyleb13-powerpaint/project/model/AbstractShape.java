/*
 * TCSS 305, Spring 2017
 * PowerPaint
 */
package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

/**
 * Abstract class for all of the drawable shapes.
 * @author Kyle Beveridge
 * @version 5/14/17
 */
public abstract class AbstractShape implements DrawShape, Cloneable {
    /**
     * Point shapes intitialize to.
     */
    protected static final Point START = new Point(-1, -1);
    
    /**
     * Point where shape starts.
     */
    protected Point myStart;
    /**
     * Point where shape ends.
     */
    protected Point myEnd;
    
    /**
     * Determines if shape is filled or empty.
     */
    protected boolean myFill;
    
    /**
     * Enable automatic updates when shape is changed.
     */
    protected boolean myAutoUpdate = true;
    
    /**
     * Construct the shape with the given points.
     * @param p1 the start.
     * @param p2 the end.
     */
    public AbstractShape(final Point p1, final Point p2) {
        myStart = (Point) p1.clone();
        myEnd = (Point) p2.clone();
    }
    
    /** 
     * Draws the shape. Implementation left to child classes.
     * @param g2 Graphics of drawing panel.
     */
    public abstract void drawShape(Graphics2D g2);

    /**
     *  Set start point of shape.
     *  @param thePoint the start.
     */
    public void setStart(final Point thePoint) {
        myStart = (Point) thePoint.clone();
        if (myAutoUpdate) {
            update();
        }
    }

    /**
     * set end point of shape.
     * @param thePoint the end. 
     */
    public void setEnd(final Point thePoint) {
        myEnd = (Point) thePoint.clone();
        if (myAutoUpdate) {
            update();
        }
    }
    
    /**
     * Sets the fill of the shape.
     * @param theFill the fill.
     */
    public void setFill(final boolean theFill) {
        myFill = theFill;
    }
    
    /**
     * Returns the 2d shape object of the class.
     * @return the shape.
     */
    public abstract Shape getState();
    
    /**
     * Finsih drawing the current shape.
     */
    public abstract void finish();
    
    @Override
    public AbstractShape clone() throws CloneNotSupportedException {
        return (AbstractShape) super.clone();
    }
    
}
