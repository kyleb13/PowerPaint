/*
 * TCSS 305, Spring 2017
 * PowerPaint
 */
package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

/**
 * Interface that defines drawable shapes.
 * @author Kyle Beveridge
 * @version 5/11/17
 */
public interface DrawShape extends Cloneable {
    /**
     * Draw the Shape.
     * @param g2 Graphics from panel.
     */
    void drawShape(Graphics2D g2);
    /**
     * Set start Point.
     * @param thePoint start point.
     */
    void setStart(Point thePoint);
    /**
     * Set end point.
     * @param thePoint end point.
     */
    void setEnd(Point thePoint);
    /**
     * Get the shape2D being used.
     * @return the shape.
     */
    Shape getState();
    
    /**
     * Update the shape being drawn.
     */
    void update();
    
    /**
     * Finish drawing the shape.
     */
    void finish();
}
