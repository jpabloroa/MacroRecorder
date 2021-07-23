/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grabador;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class Proyector extends JFrame implements Runnable {

    public Proyector(MouseListener Ml) {
        
        try {
            initComp(Ml);
        } catch (AWTException ex) {
            mensaje_error += " ¡ Error en la generación de imagen ! - Detalles : " + ex.getMessage() + "\n";
            newErrorMessages();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                setContentPane(new JLabel(new ImageIcon(new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())))));
            } catch (AWTException ex) {
                mensaje_error += " ¡ Error en la generación de imagen ! - Detalles : " + ex.getMessage() + "\n";
                newErrorMessages();
            } finally {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ie) {
                    mensaje_error += " ¡ Error en la recarga del sistema ! - Detalles : " + ie.getMessage() + "\n";
                    newErrorMessages();
                }
            }
        }
    }

    public String returnErrorMessage() {
        return mensaje_error;
    }

    private void newErrorMessages() {
        val = true;
    }

    public boolean newErrorMessage() {
        boolean result = val;
        val = false;
        return result;
    }

    private void initComp(MouseListener Ml) throws AWTException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        addMouseListener(Ml);
        setContentPane(new JLabel(new ImageIcon(new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())))));
    }

    // Variables de ejecución
    private boolean val;
    private String mensaje_error;
    private JFrame panel;
}
