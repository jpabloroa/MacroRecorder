/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import grabador.Interactor;
import java.awt.AWTException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class Panel extends javax.swing.JFrame implements MouseListener {

    /**
     *
     */
    public String dir = "";

    /**
     *
     */
    public String name;
    public Interactor ac;
    public File directorio;

    /**
     * Creates new form Panel
     */
    public Panel() {
        initComponents();
        setLocationRelativeTo(null);
        label_nombre_app.setText("<html>Grabador y ejecutor de<br>Macros - EREL</html>");
        ac = new Interactor();
        directorio = new File(ac.modify("/dimRest","/src/dimrest/"));
        
        combo.addItem("Seleccione una macro");
        actualizarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        boton_grabar = new javax.swing.JButton();
        boton_reproducir = new javax.swing.JButton();
        nombreMacro = new javax.swing.JTextField();
        combo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        label_nombre_app = new javax.swing.JLabel();
        logo_roverin = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        boton_grabar.setText("Grabar Macro");
        boton_grabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_grabarActionPerformed(evt);
            }
        });

        boton_reproducir.setText("Reproducir Macro");
        boton_reproducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reproducirActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre de la macro");

        label_nombre_app.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N

        logo_roverin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/RES.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nombreMacro)
                    .addComponent(combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(boton_reproducir, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(boton_grabar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(label_nombre_app, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(logo_roverin)))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logo_roverin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_nombre_app, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreMacro, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_reproducir)
                    .addComponent(boton_grabar))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_grabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_grabarActionPerformed
        name = nombreMacro.getText();
        if (!name.equals("") && !name.equals(" ")) {
            if (name.length() <= 2) {
                name = "defaultMacro " + name;
            }
            this.setVisible(false);
            try {
                DisplayRecorder unj = new DisplayRecorder(name);
            } catch (AWTException | IOException ex) {
                JOptionPane.showMessageDialog(null, " ¡ Error inesperado ! -> Detalles : " + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, " ¡ El nombre ingresado no es válido ! ");
        }
        actualizarTabla();
    }//GEN-LAST:event_boton_grabarActionPerformed

    private void boton_reproducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reproducirActionPerformed
        name = ac.formatName(combo.getSelectedItem().toString(), 2);
        if (!name.equals("Seleccione una macro")) {
            try {
                ac = new Interactor(name, 102);
                this.setVisible(false);
                ac.leerInstruccion();
                TimeUnit.MILLISECONDS.sleep(ac.tiempoEjec() + 2000);
            } catch (AWTException | InterruptedException | IOException ex) {
                JOptionPane.showMessageDialog(null, " ¡ Error inesperado ! \n -> Detalles : " + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, " ¡ El nombre ingresado no es válido ! ");
        }

        this.setVisible(true);
        actualizarTabla();
    }//GEN-LAST:event_boton_reproducirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Panel().setVisible(true);
            }
        });
    }

    private void actualizarTabla() {
        if (directorio.list() != null) {
            String files[] = directorio.list();
            for (String file : files) {
                combo.addItem(ac.formatName(file, 1));
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_grabar;
    private javax.swing.JButton boton_reproducir;
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel label_nombre_app;
    private javax.swing.JLabel logo_roverin;
    private javax.swing.JTextField nombreMacro;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {

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
}