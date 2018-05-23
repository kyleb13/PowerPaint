/*
 * TCSS 305, Spring 2017
 * PowerPaint
 */
package model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 * Allows the user to free draw.
 * @author Kyle Beveridge.
 * @version 5/14/17
 */
public class DrawPencil extends AbstractShape implements Cloneable {
    
    /**
     * The path of the pencil.
     */
    protected Path2D myPath;
    
    /**
     * construct pencil at default point.
     */
    public DrawPencil() {
        this(new Point(-1, -1), new Point(-1, -1));
    }
    
    /**
     * Construct pencil at specified point.
     * @param p1 the start.
     * @param p2 the end.
     */
    public DrawPencil(final Point p1, final Point p2) {
        super(p1, p2);
        myPath = new Path2D.Double();
        myAutoUpdate = false;
        myPath.moveTo(p1.getX(), p1.getY());
    }

    /**
     * Add more points to the path.
     */
    @Override
    public void update() {
        if (myPath != null && myPath.getCurrentPoint().equals(new Point(-1, -1))) {
            myPath = new Path2D.Double();
            myPath.moveTo(myStart.getX(), myStart.getY());
        }
        if (myPath != null) {
            myPath.lineTo(myEnd.getX(), myEnd.getY());
            this.setStart(myEnd);
        }
    }

    /**
     * Draw the line.
     * @param g2 Graphics from panel.
     */
    @Override
    public void drawShape(final Graphics2D g2) {
        g2.draw(myPath);
    }

    /**
     * 
     */
    @Override
    public Shape getState() {
        return (Shape) myPath.clone();
    }

    @Override
    public void finish() {
        myPath = new Path2D.Double();
        myPath.moveTo(-1, -1);
        setStart(new Point(-1, -1));
        setEnd(new Point(-1, -1));
    }
    
    @Override
    public DrawPencil clone() throws CloneNotSupportedException {
        final DrawPencil clone = (DrawPencil) super.clone();
        clone.setStart((Point) myStart.clone());
        clone.setEnd((Point) myEnd.clone());
        clone.update();
        clone.myPath = (Path2D.Double) myPath.clone();
        return clone;
    }

}
