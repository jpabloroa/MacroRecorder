/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grabador;

import java.awt.*;
import static java.awt.event.KeyEvent.*;
import java.awt.event.*;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class Accion implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        a = MouseInfo.getPointerInfo();
        b = a.getLocation();
        b = a.getLocation();
        x = b.getX();
        y = b.getY();
        System.out.println("Posición y: " + y + " ---- Posición x: " + x);
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            mensaje += " ¡ Error en la generación del Bot ! - Detalles : " + ex.getMessage() + "\n";
            newErrorMessages();
        }
        changeWindow();
        doClick();
        changeWindow();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void makeDelay(int MiliSegundos) {
        robot.delay(MiliSegundos);
    }

    private void doClick() {
        makeDelay(500);
        robot.mousePress(BUTTON1_MASK);
        robot.mouseRelease(BUTTON1_MASK);
        makeDelay(500);
    }

    private void changeWindow() {
        robot.keyPress(VK_ALT);
        robot.keyPress(VK_TAB);
        robot.keyRelease(VK_ALT);
        robot.keyRelease(VK_TAB);
    }

    public String returnErrorMessage() {
        return mensaje;
    }

    private void newErrorMessages() {
        val = true;
    }

    public boolean newErrorMessage() {
        boolean result = val;
        val = false;
        return result;
    }

    private String nombre;
    private String descripcion;
    private MouseEvent evento;
    private PointerInfo a;
    private Point b;
    private double x, y;
    private Robot robot;
    private String mensaje;
    private boolean val;

}
