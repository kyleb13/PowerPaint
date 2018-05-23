/*
 * TCSS 305, Spring 2017
 * PowerPaint
 */
package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Creates and Draws rectangles.
 * @author Kyle Beveridge
 * @version 11/15/17
 *
 */
public class DrawRectangle extends AbstractShape implements Cloneable {
    
    /**
     * Rectangle to be drawn.
     */
    private Rectangle2D myRect;
    
    /**
     * Width of rectangle.
     */
    private double myWidth;
    
    /**
     * Height of rectangle.
     */
    private double myHeight;
    
    /**
     * Construct default rectangle.
     */
    public DrawRectangle() {
        this(START, START);
    }
    
    /**
     * Construct rectangle at specified points.
     * @param p1 the start.
     * @param p2 the end.
     */
    public DrawRectangle(final Point p1, final Point p2) {
        super(p1, p2);
        myWidth = p2.getX() - p1.getX();
        myHeight = p2.getY() - p1.getY();
        myRect = new Rectangle2D.Double(p1.getX(), p1.getY(), myWidth, myHeight);
    }


    @Override
    public void update() {
        myWidth = myEnd.getX() - myStart.getX();
        myHeight = myEnd.getY() - myStart.getY();
        if (myWidth > 0 && myHeight < 0) {
            myRect.setRect(myStart.getX(), 
                myEnd.getY(), myWidth, -1 * myHeight);
        } else if (myWidth < 0 && myHeight < 0)  {
            myRect.setRect(myEnd.getX(), myEnd.getY(), 
                -1 * myWidth, -1 * myHeight);
        } else if (myWidth < 0 && myHeight > 0) {
            myRect.setRect(myEnd.getX(), 
                           myStart.getY(), -1 * myWidth, myHeight);
        } else {
            myRect.setRect(myStart.getX(), myStart.getY(), myWidth, myHeight);
        }
        
    }

    /**
     * 
     */
    @Override
    public void drawShape(final Graphics2D g2) {
        if (myFill) {
            g2.fill(myRect);
        } else {
            g2.draw(myRect);
        }
    }

    /**
     * 
     */
    @Override
    public Shape getState() {
        return (Shape) myRect.clone();
    }
    
    /**
     * 
     */
    @Override
    public void setStart(final Point thePoint) {
        myStart = (Point) thePoint.clone();
    }
    
    /**
     * 
     */
    @Override
    public void setEnd(final Point thePoint) {
        myEnd = (Point) thePoint.clone();
    }

    /**
     * 
     */
    @Override
    public void finish() {
        myRect = new Rectangle2D.Double(-1, -1, 0, 0);
    }
    
    @Override
    public DrawRectangle clone() throws CloneNotSupportedException {
        final DrawRectangle clone = (DrawRectangle) super.clone();
        clone.setStart((Point) myStart.clone());
        clone.setEnd((Point) myEnd.clone());
        clone.update();
        clone.myRect = (Rectangle2D.Double) myRect.clone();
        return clone;
    }

}
