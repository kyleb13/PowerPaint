/*
 * TCSS 305, Spring 2017
 * PowerPaint
 */
package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Erases drawn shapes. utilizes pencil tool.
 * @author Kyle Beveridge
 * @version 5/16/17
 */
public class DrawEraser extends DrawPencil {
    
    /**
     * construct the eraser.
     * @param p1 Startpoint.
     * @param p2 end point.
     */
    public DrawEraser(final Point p1, final Point p2) {
        super(p1, p2);
    }

    /**
     * Construct a defualt eraser.
     */
    public DrawEraser() {
        super();
    }
    

    @Override
    public void drawShape(final Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.draw(myPath);
    }

}
