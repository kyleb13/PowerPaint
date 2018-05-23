/*
 * TCSS 305, Spring 2017
 * PowerPaint
 */
package model;

import java.awt.Point;

/**
 * A tool that can draw circles.
 * @author Kyle Beveridge
 * @version 5/16/17.
 */
public class DrawCircle extends DrawEllipse {

    /**
     * Construct a circle at default points.
     */
    public DrawCircle() {
        this(START, START);
    }
    
    /**
     * Construct circle at given points.
     * @param p1 .
     * @param p2 .
     */
    public DrawCircle(final Point p1, final Point p2) {
        super(p1, p2);
    }
    
    /**
     * Update the shape when cursor moves.
     */
    @Override
    public void update() {
        final double deltaX = myEnd.getX() - myStart.getX();
        final double deltaY = myEnd.getY() - myStart.getY();
        correct(deltaX, deltaY);
        super.update();
    }
    
    /**
     * Correct the start and end points to make the shape a perfect circle.
     * @param x1 the x length.
     * @param y1 the y length.
     */
    private void correct(final double x1, final double y1) {
        if (Math.abs(x1) < Math.abs(y1)) {
            if (y1 > 0) {
                myEnd.setLocation(myEnd.getX(), myEnd.getY() - (Math.abs(y1) - Math.abs(x1)));
            } else {
                myEnd.setLocation(myEnd.getX(), myEnd.getY() + (Math.abs(y1) - Math.abs(x1)));
            }
        } else if (Math.abs(x1) > Math.abs(y1)) {
            if (x1 > 0) {
                myEnd.setLocation(myEnd.getX()  - (Math.abs(x1) - Math.abs(y1)), myEnd.getY());
            } else {
                myEnd.setLocation(myEnd.getX()  + (Math.abs(x1) - Math.abs(y1)), myEnd.getY());
            }
        }
    }

}
