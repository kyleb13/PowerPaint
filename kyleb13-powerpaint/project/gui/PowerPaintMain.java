/*
 * TCSS 305, Spring 2017 
 */
package gui;

import java.awt.EventQueue;

/**
 * 
 * @author Kyle Beveridge
 * @version 5/2/17
 */
public final class PowerPaintMain {
    /**
     * 
     */
    private PowerPaintMain() { }
    
    /**
     * 
     * @param theArgs .
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PowerPaintGui().start();
            }
        });
    }
}