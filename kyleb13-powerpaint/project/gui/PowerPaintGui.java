/*
 * TCSS 305, Spring 2017 
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.MouseEvents;

/**
 * Creates and Displays the program's gui.
 * @author Kyle Beveridge
 * @version 5/2/17
 */

public class PowerPaintGui extends JPanel implements PropertyChangeListener {
    
    /**
     * Size of Drawing Area.
     */
    private static final Dimension SIZE = new Dimension(400, 400);
    
    /**
     * Icon used by the program.
     */
    private static final ImageIcon ICON = new ImageIcon("./images/icon.png");
    
    /**
     * Auto-generated serialization uid.
     */
    private static final long serialVersionUID = 1183707285564592159L;
    
    /**
     * RGB values for uw colors.
     */
    private static final int[] UW_SRGB_VALUES = {51, 111, 232, 211, 162};
    
    /**
     * The actions for all of the tool buttons.
     */
    private AbstractAction[] myButts;

    /**
     * Main frame of the program.
     */
    private final JFrame myFrame = new JFrame("PowerPaint");
    
    /**
     * Names of Toolbar Buttons.
     */
    private final String[] myToolbarNames = {"Pencil", "Line", "Rectangle", 
        "Square", "Ellipse", "Circle", "Eraser"};
    
    /**
     * The custom jpanel that is drawn on.
     */
    private final DrawPanel myDrawPanel = new DrawPanel();
    
    /**
     * Main Button action. 
     */
    private ButtonAction myButtAction;
    
    /**
     * The main color, used on left click.
     */
    private Color myPrimaryColor = new Color(UW_SRGB_VALUES[0], 0, UW_SRGB_VALUES[1]);
    
    /**
     * Secondary color, used on right click.
     */
    private Color mySecondaryColor = new Color(UW_SRGB_VALUES[2], 
                                               UW_SRGB_VALUES[2 + 1], UW_SRGB_VALUES[2 + 2]);
    
    /**
     *  Custom mouse listener used by the panel. 
     */
    private final MouseEvents myEvents = new MouseEvents(myDrawPanel, 
                                                         myPrimaryColor, mySecondaryColor);
    
    /**
     * The clear button.
     */
    private final JMenuItem myClear = new JMenuItem("Clear");
    
    
    /**
     * Initialize core gui features, call methods to set up specifics.
     */
    public void start() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } catch (final InstantiationException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        } catch (final UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        buttonActionSetup();
        myFrame.setIconImage(ICON.getImage());
        this.setLayout(new BorderLayout());
        myDrawPanel.setPreferredSize(SIZE);
        myDrawPanel.setBackground(Color.WHITE);
        myDrawPanel.setColor(myPrimaryColor);
        myDrawPanel.addPropertyChangeListener(this);
        this.add(myDrawPanel, BorderLayout.CENTER);
        this.setBackground(Color.WHITE);
        myFrame.add(this, BorderLayout.CENTER);
        
        toolbarSetup();
        menuSetup();
        
        this.addMouseListener(myEvents);
        this.addMouseMotionListener(myEvents);
        myFrame.setContentPane(this);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    /**
     * Set up toolbar buttons.
     */
    private void toolbarSetup() {
        final JToolBar toolbar = new JToolBar();
        final ButtonGroup group = new ButtonGroup();
        initButtons(toolbar, group);
        add(toolbar, BorderLayout.SOUTH);
     
    }
    
    /**
     * Sets up actions for all the tool buttons.
     */
    private void buttonActionSetup() {
        myButtAction = new ButtonAction(myDrawPanel);
        myButts = new AbstractAction[2 + 2 + 2 + 1];
        myButts[0] = myButtAction.
            new PencilAction(myToolbarNames[0], new ImageIcon("./images/pencil_bw.gif"));
        myButts[1] = myButtAction.
            new LineAction(myToolbarNames[1], new ImageIcon("./images/line_bw.gif"));
        myButts[2] =  myButtAction.new RectangleAction(
            myToolbarNames[2], new ImageIcon("./images/rectangle_bw.gif"));
        myButts[2 + 1] = myButtAction.
            new SquareAction(myToolbarNames[2 + 1], new ImageIcon("./images/square_bw.gif"));
        myButts[2 + 2] = myButtAction.new EllipseAction(
            myToolbarNames[2 * 2], new ImageIcon("./images/ellipse_bw.gif"));
        myButts[2 + 2 + 1] = myButtAction.new CircleAction(
            myToolbarNames[2 * 2 + 1], new ImageIcon("./images/circle_bw.gif"));
        myButts[2 + 2 + 2] = myButtAction.new EraserAction(
            myToolbarNames[2 * 2 + 2], new ImageIcon("./images/eraser_bw.gif"));
    }
    
    /**
     * Sets up the core of the menu bar.
     */
    private void menuSetup() {
        final JMenuBar bar = new JMenuBar();
        final JMenu file = new JMenu("File");
        final JMenu options = new JMenu("Options");
        final JMenu tools = new JMenu("Tools");
        final JMenu help = new JMenu("Help");
        
        file.setMnemonic(KeyEvent.VK_F);
        options.setMnemonic(KeyEvent.VK_O);
        tools.setMnemonic(KeyEvent.VK_T);
        help.setMnemonic(KeyEvent.VK_H);
        
        myClear.setEnabled(false);
        myClear.setMnemonic(KeyEvent.VK_C);
        myClear.addActionListener(e -> myDrawPanel.clearScreen());
        myClear.addActionListener(e -> myClear.setEnabled(false));
        file.add(myClear);
        file.addSeparator();
        
        final JMenuItem quit = new JMenuItem("Quit");
        quit.setMnemonic(KeyEvent.VK_Q);
        quit.addActionListener(e -> {
            myFrame.setVisible(false);
            myFrame.dispose();
        });
        file.add(quit);
        
        optionSetup(options);
        toolSetup(tools);
        
        final JMenuItem about = new JMenuItem("About...");
        about.setMnemonic(KeyEvent.VK_A);
        final String message = "Kyle Beveridge\nSpring 2017\nTCSS 305 PowerPaint";
        about.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, message, about.getText(), 
                JOptionPane.INFORMATION_MESSAGE, ICON);
        });
        help.add(about);
        bar.add(file);
        bar.add(options);
        bar.add(tools);
        bar.add(help);
        
        myFrame.setJMenuBar(bar);
    }
    
    /**
     * Initializes the Toolbar Buttons.
     * @param bar1 the bar the buttons are added to.
     * @param theGroup Button group of the buttons.
     */
    private void initButtons(final JToolBar bar1, final ButtonGroup theGroup) {
        final JToggleButton button = new JToggleButton(myButts[0]);
        bar1.add(button);
        theGroup.add(button);
        
        final JToggleButton button1 = new JToggleButton(myButts[1]);
        button1.setSelected(true);
        bar1.add(button1);
        theGroup.add(button1);
        
        final JToggleButton button2 = new JToggleButton(myButts[2]);
        bar1.add(button2);
        theGroup.add(button2);
        
        final JToggleButton button3 = new JToggleButton(myButts[2 + 1]);
        bar1.add(button3);
        theGroup.add(button3);
        
        final JToggleButton button4 = new JToggleButton(myButts[2 + 2]);
        bar1.add(button4);
        theGroup.add(button4);
        
        final JToggleButton button5 = new JToggleButton(myButts[2 * 2 + 1]);
        bar1.add(button5);
        theGroup.add(button5);
        
        final JToggleButton button6 = new JToggleButton(myButts[2 * 2 + 2]);
        bar1.add(button6);
        theGroup.add(button6);
        
        myButtAction.setGroup1(theGroup);
    }
    
    /**
     * Sets up the option menu.
     * @param theOptions the Menu the options are added to.
     */
    private void optionSetup(final JMenu theOptions) {
        final JMenu thickness = new JMenu("Thickness");
        thickness.setMnemonic(KeyEvent.VK_T);
        final JSlider slider = new JSlider(0, 20, 1);
        slider.setMajorTickSpacing(2 * 2 + 1);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(myDrawPanel);
        thickness.add(slider);
        theOptions.add(thickness);
        
        final JColorChooser primary = new JColorChooser();
        final JColorChooser secondary = new JColorChooser();
        primary.setColor(UW_SRGB_VALUES[0], 0, UW_SRGB_VALUES[1]);
        secondary.setColor(UW_SRGB_VALUES[2], UW_SRGB_VALUES[2 + 1], UW_SRGB_VALUES[2 + 2]);
        myPrimaryColor = primary.getColor();
        mySecondaryColor = secondary.getColor();
        final ColorIcon icon1 = new ColorIcon(myPrimaryColor);
        final ColorIcon icon2 = new ColorIcon(mySecondaryColor);
        final ColorAction a1 = new ColorAction("Primary...", icon1);
        a1.setColor(myPrimaryColor);
        final ColorAction a2 = new ColorAction("Secondary...", icon2);
        a2.setColor(mySecondaryColor);
        final JMenuItem primButton = new JMenuItem(a1);
        final JMenuItem secButton = new JMenuItem(a2);
        
        primButton.setMnemonic(KeyEvent.VK_P);
        secButton.setMnemonic(KeyEvent.VK_S);
        
        primButton.addActionListener(e -> { 
            myPrimaryColor = JColorChooser.showDialog(primary, "Chose A Primary Color"
                                                      , myPrimaryColor);
            myEvents.setPrimary(myPrimaryColor);
            icon1.setColor(myPrimaryColor);
            a1.setColor(myPrimaryColor);
            primButton.firePropertyChange("PrimColor", 0, 1);
        });
        secButton.addActionListener(e -> { 
            mySecondaryColor = JColorChooser.showDialog(primary, "Chose A Secondary Color"
                                                      , mySecondaryColor);
            myEvents.setSecondary(mySecondaryColor);
            icon2.setColor(mySecondaryColor);
            a2.setColor(mySecondaryColor);
            secButton.firePropertyChange("SecColor", 0, 1);
        });
        theOptions.addSeparator();
        theOptions.add(primButton);
        theOptions.add(secButton);
        theOptions.addSeparator();
        final JCheckBox fill = new JCheckBox("Fill");
        fill.setMnemonic(KeyEvent.VK_F);
        fill.addActionListener(e -> myDrawPanel.setFill(fill.isSelected()));
        theOptions.add(fill);
    }
    
    /**
     * Sets up menu bar tool buttons.
     * @param theMenu Menu buttons are added to.
     */
    private void toolSetup(final JMenu theMenu) {
        final ButtonGroup toolGroup = new ButtonGroup();
        final JRadioButton[] menuRadioGroup = new JRadioButton[2 * 2 * 2 + 1];
        int cnt =  0;
        for (final AbstractAction s: myButts) {
            final JRadioButton b = new JRadioButton(s);
            if (cnt == 1) {
                b.setSelected(true);
            }
            theMenu.add(b);
            menuRadioGroup[cnt++] = b;
            toolGroup.add(b);
        }
        myButtAction.setGroup2(menuRadioGroup);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt1) {
        final DrawPanel source = (DrawPanel) evt1.getSource();
        if (evt1.getPropertyName().equals("history size")) {
            if (source.getHistSize() > 0) {
                myClear.setEnabled(true);
            } else {
                myClear.setEnabled(false);
            }
        }
    }   
}