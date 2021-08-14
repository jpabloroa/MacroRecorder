/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grabador;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class Accion extends Interactor implements MouseListener, KeyListener {

    /**
     *
     * @param panel
     * @param imagen
     * @param Nombre_Macro
     * @param process
     * @throws AWTException
     * @throws java.io.IOException
     */
    public Accion(String Nombre_Macro, JFrame panel, JLabel imagen, int process) throws AWTException, IOException {
        super(Nombre_Macro, process);
        this.error = new StringBuffer();
        this.report = new StringBuffer();
        this.cmd = new StringBuffer();
        this.robot = new Robot();
        this.screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenRect = new Rectangle(this.screen);
        this.panel = panel;
        this.imagen = imagen;
        imagen.setIcon(new ImageIcon(getScaledImage()));
    }

    /**
     *
     * @return @throws IOException
     */
    public String performErel() throws IOException {
        super.leerInstruccion();
        return "done";
    }

    /**
     *
     * @param comd
     */
    public void newRecord(String comd) {
//        if (isRecording) {
//            String op = stopTimer() + comd;
//            super.grabar(comd);
//            startTimer();
//        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int btnmsk = MouseEvent.getMaskForButton(e.getButton());
        int x = e.getX();
        int y = e.getY();
        this.panel.setVisible(false);
        double[] res = obtain(e.getX(), e.getY());
        robot.mouseMove((int) res[0], (int) res[1]);
        robot.mousePress(btnmsk);
        robot.delay(100);
        robot.mouseRelease(btnmsk);
        robot.delay(150);
        this.panel.setVisible(true);
        robot.mouseMove(e.getXOnScreen(), e.getYOnScreen());
        imagen.setIcon(new ImageIcon(getScaledImage()));
        super.ew.addClickEvent("do", e.getButton(), (int) res[0], (int) res[1]);
        super.ew.addClickEvent("stop", e.getButton(), (int) res[0], (int) res[1]);
        report.append(Calendar.getInstance().getTime().toString()).append(" ¡ Nuevo evento registrado ! - > click: ").append(e.getButton()).append("\n");
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

    @Override
    public void keyTyped(KeyEvent e) {
        int keycode = KeyEvent.getExtendedKeyCodeForChar(e.getKeyChar());
        this.panel.setVisible(false);
        robot.keyPress(keycode);
        robot.delay(100);
        robot.keyRelease(keycode);
        this.panel.setVisible(true);
        imagen.setIcon(new ImageIcon(getScaledImage()));
        super.ew.addKeyEvent("do", keycode);
        super.ew.addKeyEvent("stop", keycode);
        report.append(Calendar.getInstance().getTime().toString()).append(" ¡ Nuevo evento registrado ! - > char: ").append(e.getKeyChar()).append("\n");
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     *
     * @param opc
     * @throws java.awt.AWTException
     */
    public void funcAct(int opc) throws AWTException {
        int key = 0;
        int k1 = 0, k2 = 0;
        switch (opc) {
            case 1:
                key = KeyEvent.VK_COPY;
                k1 = KeyEvent.VK_CONTROL;
                k2 = KeyEvent.VK_C;
                break;
            case 2:
                key = KeyEvent.VK_PASTE;
                k1 = KeyEvent.VK_CONTROL;
                k2 = KeyEvent.VK_V;
                break;
            default:
                throw new AWTException(" ¡ La función especificada no es válida ! ");
        }
        this.panel.setVisible(false);
        robot.keyPress(key);
        robot.keyRelease(key);
        this.panel.setVisible(true);
        imagen.setIcon(new ImageIcon(getScaledImage()));
        newRecord(setCntrComd(k1, k2, 100));
        report.append(Calendar.getInstance().getTime().toString()).append(" ¡ Nuevo evento registrado ! - > char: ").append(KeyEvent.getExtendedKeyCodeForChar(KeyEvent.VK_COPY)).append("\n");
    }

    private String setCntrComd(int Key1, int Key2, int wait) {
        return new StringBuffer().append("do key press -").append(KeyEvent.getExtendedKeyCodeForChar(Key1)).append("; ").append("do key press -").append(KeyEvent.getExtendedKeyCodeForChar(Key2)).append("; ").append("do wait -").append(wait).append("; ").append("stop key press -").append(KeyEvent.getExtendedKeyCodeForChar(Key1)).append("; ").append("stop key press -").append(KeyEvent.getExtendedKeyCodeForChar(Key2)).append("; ").toString();
    }

    /**
     *
     * @param e
     * @param x
     * @param y
     * @return
     */
    private String setMouseCmd(MouseEvent e, int x, int y, int wait) {
        return new StringBuffer().append("do click press -").append(e.getButton()).append(" at -").append(x).append(" -").append(y).append("; ").append("do wait -").append(wait).append("; ").append("stop click press -").append(e.getButton()).append(" at -").append(x).append(" -").append(y).append("; ").toString();

    }

    /**
     *
     * @param e
     * @param x
     * @param y
     * @return
     */
    private String setKeyCmd(KeyEvent e, int wait) {
        return new StringBuffer().append("do key press -").append(e.getKeyChar()).append("; ").append("do wait -").append(wait).append("; ").append("stop key press -").append(e.getKeyChar()).append("; ").toString();
    }

    /**
     *
     * @param x
     * @param y
     * @return
     * @throws AWTException
     */
    private double[] obtain(int x, int y) {
        double propx = x / (screen.getWidth() * prop);
        double propy = y / (screen.getHeight() * prop);

        double intx = screen.getWidth() * propx;
        double inty = screen.getHeight() * propy;

        return new double[]{intx, inty};
    }

    /**
     *
     * @return @throws AWTException
     */
    private Image getScaledImage() {
        Dimension dim = this.panel.getSize();
        this.panel.setSize(0, 0);
        try {
            this.imageBuffer = new Robot().createScreenCapture(screenRect);
        } catch (AWTException ex) {
            error.append(Calendar.getInstance().getTime().toString()).append(" -> ¡ Nuevo error en la instrucción getScaledImage ! \n");
        }
        this.panel.setSize(dim);
        this.h = this.imageBuffer.getHeight() * prop;
        this.w = this.imageBuffer.getWidth() * prop;
        //----------------------------------------------------------------------
        resizedImg = new BufferedImage((int) this.w, (int) this.h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(imageBuffer, 0, 0, (int) this.w, (int) this.h, null);
        g2.dispose();
        return resizedImg;
    }

    /**
     *
     * @return
     */
    public String getErrorMessages() {
        return error.toString();
    }

    /**
     *
     * @return
     */
    public String getEventMessages() {
        return report.toString();
    }

    /**
     *
     */
    public double h, w;

    /**
     *
     */
    private final JFrame panel;
    private final JLabel imagen;
    private final StringBuffer error, report, cmd;
    private final double prop = 0.5;
    private final Robot robot;
    private final Dimension screen;
    private final Rectangle screenRect;
    private BufferedImage imageBuffer, resizedImg;
}
