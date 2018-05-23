/*
 * TCSS 305, Spring 2017 
 */
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.AbstractShape;
import model.DrawEraser;
import model.DrawLine;

/**
 * Creates a JPanel that can be drawn on.
 * @author Kyle Beveridge
 * @version 5//11/17
 */
public class DrawPanel extends JPanel implements ChangeListener {
    /**
     * Auto-generated serialization uid. 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * RGB values of uw colors.
     */
    private static final int[] UW_SRGB_VALUES = {51, 111};
    
    /**
     *  The location drawn shapes are initialized to. 
     */
    private static final AbstractShape INIT = 
                    new DrawLine(new Point(-1, -1), new Point(-1, -1));
    
    /**
     * Used for the clearScreen method.
     */
    private static final Point MAX = new Point(3840, 2160);
    
    /**
     * Current color.
     */
    private Color myColor;
    
    /**
     * Current shape being drawn.
     */
    private AbstractShape myShape;
    
    
    /**
     * Thickness of drawn shapes. 
     */
    private int myThickness = 1;
    
    /**
     * Indicates whether shapes are to be filled or not.
     */
    private boolean myFilled;
    
    /**
     * List of previously drawn shapes.
     */
    private final List<StateStore> myHistory = new LinkedList<StateStore>();
    
    /**
     *  0 Arg Constructor for DrawPanel. Sets fields to default values.
     */
    public DrawPanel() {
        this(new Color(UW_SRGB_VALUES[0], 0, UW_SRGB_VALUES[1]), INIT, 1, false);
    }
    
    /**
     * 4 arg constructor that initializes fields according to args.
     * @param theColor color to be used.
     * @param theShape Shape the starting shape.
     * @param theThickness the intitial thickness.
     * @param theFilled Set shapes to fill or not.
     */
    public DrawPanel(final Color theColor, final AbstractShape theShape, 
                     final int theThickness, final boolean theFilled) {
        super();
        myColor = theColor;
        myShape = theShape;
        myThickness = theThickness;
        myFilled = theFilled;
    }
    
    @Override
    public void paintComponent(final Graphics g1) {
        if (g1 != null) {
            super.paintComponent(g1);
            redraw((Graphics2D) g1);
            ((Graphics2D) g1).setStroke(new BasicStroke(myThickness));
            paintComponent((Graphics2D) g1, myShape);
        }

    }
    
    /**
     * Private twin to 1 arg paintComponent(). Takes in the shape to be drawn.
     * @param g1 The graphics object of the panel.
     * @param shape1 The shape to be drawn..
     */
    private void paintComponent(final Graphics2D g1, final AbstractShape shape1) {
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
        g1.setColor(myColor);
        myShape.update();
        g1.setStroke(new BasicStroke(myThickness));
        shape1.drawShape(g1);
        this.repaint();
    }
    
    /**
     * Sets the color being used.
     * @param theColor the new color.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    /**
     * Sets the shape to be drawn.
     * @param theShape Shape to be drawn.
     */
    public void setShape(final AbstractShape theShape) {
        myShape = theShape;
    }
    
    /**
     * Sets the thickness of drawn shapes.
     * @param theStroke the thickness.
     */
    public void setThickness(final int theStroke) {
        myThickness = theStroke;
    }
    
    /**
     * Set the start point of the shape.
     * @param p1 Point to start at.
     */
    public void setStart(final Point p1) {
        myShape.setStart(p1);
    }
    
    /**
     * Set the end point of the shape.
     * @param p1 the end point.
     */
    public void setEnd(final Point p1) {
        myShape.setEnd(p1);
    }
    
    /**
     * Sets whether to fill shapes or not.
     * @param theFill boolean indicating the choice.
     */
    public void setFill(final boolean theFill) {
        myFilled = theFill;
        myShape.setFill(theFill);
    }
    
    /**
     * Called on mouse release. Finishes drawing shape, adds
     * to history, and prepares next shape. 
     */
    public void finishDraw() {
        if (myShape instanceof DrawEraser) {
            myHistory.add(new StateStore(myShape.getState(), myFilled, Color.WHITE, new 
                BasicStroke(myThickness)));
        } else {
            myHistory.add(new StateStore(myShape.getState(), myFilled, myColor, 
                new BasicStroke(myThickness)));
        }
        firePropertyChange("history size", myHistory.size() - 1, myHistory.size());
        redraw((Graphics2D) this.getGraphics());
        myShape.finish();
        
    }
    
    /**
     * Get the number of shapes drawn.
     * @return the number of shapes.
     */
    public int getHistSize() {
        return myHistory.size();
    }
    
    /**
     * Redraws all of the previous shapes.
     * @param g2 Graphics object from the panel.
     */
    private void redraw(final Graphics2D g2) {
        if (g2 != null) {
            final Color initColor = g2.getColor();
            final Stroke initStroke = g2.getStroke();
            final boolean initFill = myFilled;
            for (final StateStore s: myHistory) {
                g2.setColor(s.myC);
                ((Graphics2D) g2).setStroke(s.myStroke);
                if (s.myMode) {
                    ((Graphics2D) g2).fill(s.myDraw);
                } else {
                    ((Graphics2D) g2).draw(s.myDraw);
                }
            }
            this.setFill(initFill);
            g2.setColor(initColor);
            g2.setStroke(initStroke);
        }
        this.repaint();
    }
    
    /**
     * Clears all of the previously drawn shapes.
     */
    public void clearScreen() {
        myHistory.add(new StateStore(new Rectangle2D.Double(0, 0, MAX.getX(), MAX.getY()), 
            true, Color.WHITE, new BasicStroke(myThickness)));
        redraw((Graphics2D) this.getGraphics());
        myHistory.clear();
    }
    
    /**
     * Clones the current shape for storage in history.
     * @return A clone of the shape.
     * @throws CloneNotSupportedException 
     */
    public AbstractShape getFinalShape() throws CloneNotSupportedException {
        return myShape.clone();
    }
    
    @Override
    public void stateChanged(final ChangeEvent arg0) {
        final JSlider source = (JSlider) arg0.getSource();
        this.setThickness(source.getValue());
    }
    
    /**
     * Inner class used to store the state of previously
     * drawn shapes.
     * @author Kyle Beveridge
     */
    private class StateStore {
        /**
         * The shape that was drawn.
         */
        private final Shape myDraw;
        /**
         * Whether the shape was filled or not.
         */
        private final boolean myMode;
        /**
         * The color of the shape.
         */
        private final Color myC;
        
        /**
         * The thickness of the shape.
         */
        private final Stroke myStroke;
        
        /**
         * 4 arg constructor that stores the pertinent info of the shape.
         * @param s1 the shape.
         * @param mode1 the fill.
         * @param c1 the color.
         * @param theStroke the thickness.
         */
        StateStore(final Shape s1, final boolean mode1, final Color c1, 
                   final Stroke theStroke) {
            myDraw = s1;
            myMode = mode1;
            myC = c1;
            myStroke = theStroke;
        }
        
    }
}
