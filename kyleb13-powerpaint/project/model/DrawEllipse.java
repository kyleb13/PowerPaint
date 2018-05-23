/*
 * TCSS 305, Spring 2017
 * PowerPaint
 */
package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Creates and draw ellipses.
 * @author Kyle Beveridge
 * @version 5/16/17
 */
public class DrawEllipse extends AbstractShape implements Cloneable {
    /**
     * The ellipse to be drawn.
     */
    private Ellipse2D myEllipse = new Ellipse2D.Double();
    /**
     * the width of the ellipse.
     */
    private double myWidth;
    /**
     * the height of the ellipse.
     */
    private double myHeight;
    
    /**
     * Construct a default ellipse.
     */
    public DrawEllipse() {
        this(START, START);
    }
    
    /**
     * Construct ellipse at specified points.
     * @param p1 the start.
     * @param p2 the end.
     */
    public DrawEllipse(final Point p1, final Point p2) {
        super(p1, p2);
        myWidth = p2.getX() - p1.getX();
        myHeight = p2.getY() - p1.getY();
        myEllipse = new Ellipse2D.Double(p1.getX(), p1.getY(), myWidth, myHeight);
    }

    /**
     * Update the shape. Changes start and end points depending on where
     * the end is in relation to the start.
     */
    @Override
    public void update() {
        myWidth = myEnd.getX() - myStart.getX();
        myHeight = myEnd.getY() - myStart.getY();
        if (myWidth > 0 && myHeight < 0) {
            myEllipse.setFrame(myStart.getX(), 
                myEnd.getY(), myWidth, -1 * myHeight);
        } else if (myWidth < 0 && myHeight < 0)  {
            myEllipse.setFrame(myEnd.getX(), myEnd.getY(), 
                -1 * myWidth, -1 * myHeight);
        } else if (myWidth < 0 && myHeight > 0) {
            myEllipse.setFrame(myEnd.getX(), 
                           myStart.getY(), -1 * myWidth, myHeight);
        } else {
            myEllipse.setFrame(myStart.getX(), myStart.getY(), myWidth, myHeight);
        }
        
    }

    @Override
    public void drawShape(final Graphics2D g2) {
        if (myFill) {
            g2.fill(myEllipse);
        } else {
            g2.draw(myEllipse);
        }

    }

    @Override
    public Shape getState() {
        return (Shape) myEllipse.clone();
    }

    @Override
    public void finish() {
        myEllipse = new Ellipse2D.Double(-1, -1, 0, 0);

    }
    
    @Override
    public DrawEllipse clone() throws CloneNotSupportedException {
        final DrawEllipse clone = (DrawEllipse) super.clone();
        clone.setStart((Point) myStart.clone());
        clone.setEnd((Point) myEnd.clone());
        clone.update();
        clone.myEllipse = (Ellipse2D.Double) myEllipse.clone();
        return clone;
    }
}
