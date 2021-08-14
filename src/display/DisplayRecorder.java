/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import grabador.Accion;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class DisplayRecorder {

    //
    private final JFrame frm = new JFrame();
    private final JPanel pnl = new JPanel();
    private final JPanel pnlBajo = new JPanel();
    private final JButton copyBtn = new JButton("Copiar");
    private final JButton pasteBtn = new JButton("Pegar");
    private final JButton finBtn = new JButton("Finalizar");
    private final JLabel lab = new JLabel();
    private final Accion act;
    
    private final double prop = 0.5;

    /**
     *
     * @param Direc
     * @throws java.awt.AWTException
     * @throws java.io.IOException
     */
    public DisplayRecorder(String Direc) throws AWTException, IOException {

        //
        act = new Accion(Direc, frm, lab, 101);

        //
        copyBtn.setPreferredSize(new Dimension(150, 30));
        copyBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    act.funcAct(1);
                } catch (AWTException ex) {
                }
            }
        });
        pasteBtn.setPreferredSize(new Dimension(150, 30));
        pasteBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    act.funcAct(2);
                } catch (AWTException ex) {
                }
            }
        });
        finBtn.setPreferredSize(new Dimension(150, 30));
        finBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    act.generarInstrucccion();
                    frm.setVisible(false);
                    new Panel().setVisible(true);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, " ¡ Error de generación de EREL ! \n Detalles -> " + ex.getMessage());
                }
            }
        });

        //        
        pnlBajo.add(copyBtn);
        pnlBajo.add(pasteBtn);
        pnlBajo.add(finBtn);

        //
        lab.setPreferredSize(new Dimension((int) act.w, (int) act.h));
        pnl.setPreferredSize(new Dimension((int) act.w, (int) act.h));

        //
        pnl.add(lab);
        pnl.addMouseListener(act);

        //
        frm.add(pnlBajo, BorderLayout.SOUTH);
        frm.add(pnl, BorderLayout.CENTER);

        //
        frm.setFocusable(true);
        frm.addKeyListener(act);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // EDIT
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        frm.setLocation(dim.width / 2 - frm.getSize().width / 2, dim.height / 2 - frm.getSize().height / 2);
//        frm.setLocationRelativeTo(null);
        frm.pack();
        frm.setVisible(true);
    }
}
