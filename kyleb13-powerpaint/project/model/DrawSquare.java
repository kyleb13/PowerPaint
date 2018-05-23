/*
 * TCSS 305, Spring 2017
 * PowerPaint
 */
package model;

import java.awt.Point;

/**
 * Create and draw squares.
 * @author Kyle Beveridge
 * @version 5/16/17
 */
public class DrawSquare extends DrawRectangle {
    
    /**
     * Draw default square.
     */
    public DrawSquare() {
        super();
    }
    
    /**
     * Draw square at specified points.
     * @param p1 Start point.
     * @param p2 End Point.
     */
    public DrawSquare(final Point p1, final Point p2) {
        super(p1, p2);
    }
    
    @Override
    public void update() {
        final double deltaX = myEnd.getX() - myStart.getX();
        final double deltaY = myEnd.getY() - myStart.getY();
        correct(deltaX, deltaY);
        super.update();
    }
    
    /**
     * Correct bounding points if they are not square.
     * @param x1 X length.
     * @param y1 Y Length.
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
