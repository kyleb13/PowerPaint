/*
 * TCSS 305, Spring 2017
 * PowerPaint
 */
package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * Create and draw lines.
 * @author Kyle Beveridge
 * @version 5/15/17
 *
 */
public class DrawLine extends AbstractShape implements Cloneable {
    
    /**
     * The line to draw.
     */
    private Line2D myLine;
    
    /**
     * Construct a default line.
     */
    public DrawLine() {
        this(new Point(-1, -1), new Point(-1, -1));
    }
    
    /**
     * Construct line between specified points.
     * @param p1 Start point.
     * @param p2 end point.
     */
    public DrawLine(final Point p1, final Point p2) {
        super(p1, p2);
        myLine = new Line2D.Double(p1, p2);
    }
    
    /**
     * Draw a line.
     * @param g2 Graphics object from panel.
     */
    @Override
    public void drawShape(final Graphics2D g2) {
        g2.draw(myLine);
    }

    @Override
    public Shape getState() {
        return (Line2D.Double) myLine.clone();
    }


    @Override
    public void update() {
        myLine.setLine(myStart, myEnd);   
    }
    
    @Override
    public void finish() {
        myLine = new Line2D.Double(new Point(-1, -1), new Point(-1, -1));
    }
    
    @Override
    public DrawLine clone() throws CloneNotSupportedException {
        final DrawLine clone = (DrawLine) super.clone();
        clone.setStart((Point) myStart.clone());
        clone.setEnd((Point) myEnd.clone());
        clone.update();
        clone.myLine = (Line2D.Double) myLine.clone();
        return clone;
    }
}