/*
 * TCSS 305, Spring 2017 
 */
package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

/**
 * Updates color icons when the color is changed.
 * @author Kyle Beveridge
 * @version 5/16/17.
 */
public class ColorAction extends AbstractAction {

    /**
     * Auto-generated serialization uid.  
     */
    private static final long serialVersionUID = -8180233820852904554L;
    
    /**
     * Color of the icon.
     */
    private Color myColor = Color.WHITE; 
    
    /**
     * The icon itself.
     */
    private final ColorIcon myIcon;
    
    
    /**
     * Initialize the color action.
     * @param name1 The name.
     * @param icon1 The color.
     */
    public ColorAction(final String name1, final Icon icon1) {
        super(name1, icon1);
        myIcon = (ColorIcon) icon1;
    }
    
    /**
     * Set color of the color action.
     * @param c1 the color.
     */
    public void setColor(final Color c1) {
        myColor = c1;
    }
    
    /**
     * Set the name of the buttons.
     * @param s1 The name.
     */
    public void setName(final String s1) {
        putValue(Action.NAME, s1);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e1) {
        myIcon.setColor(myColor);
        putValue(Action.SMALL_ICON, new ColorIcon(myColor));
    }

}
