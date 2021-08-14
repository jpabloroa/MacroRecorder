package display;

import grabador.Interactor;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * Clase Recorder - Obsoleta
 * 
 * 
 * @author Juan Pablo - Roverin Technologics
 */
public class Recorder extends JFrame implements ChangeListener {

    public static Interactor st;
//    public static String dir = "D:\\OneDrive\\Escritorio\\records\\";
//    public static String nam;

    public Recorder() throws AWTException {
        super("Grabador de pantalla");

        JTextArea ar = new JTextArea();
        ar.setBounds(0, 0, 5, 5);
        ar.setFont(new Font("Arial", 0, 5));
        ar.setForeground(new Color(255, 255, 255));

        //
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //
        defaultBackground = getBackground();

        // Se crea un JLabel
        label = new JLabel();

        // Se inicializa el JPanel
        panel = new JPanel();

        // Se crea un JSlider
        slider = new JSlider(10, 90, 50);

        //
//        act = new Accion();
        // Configuración de aspecto del JSlider
        slider.setPaintTrack(true);
//        slider.setPaintTicks(true);
//        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);

        // Se establece el evento y su contenedor (this)
        slider.addChangeListener(this);

//        addMouseListener(act);
//        addKeyListener(act);

//        Accion act = new Action();
//        
//        act.
        //
        boton1 = new JButton("Detener grabación");
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    st.generarInstrucccion();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
                }
            }
        });

        // Se agregan al JPanel
//        panel.add(ar);
        panel.add(slider);
        panel.add(label);
        panel.add(boton1);

        //
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(panel);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setSize(800, 600);
        setLocationRelativeTo(null);

        //
        setBackground(new Color(defaultBackground.getRed(), defaultBackground.getGreen(), defaultBackground.getBlue(), 150));

//        try {
//            st = new Interactor(JOptionPane.showInputDialog("Mensaje : "));
//            st.grabar();
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(null, "Error : " + ex.getMessage());
//        }

        // Se establece el texto del JLabel
        label.setText("Nivel de tranparencia : " + (100 - slider.getValue()) + "%");

        // Se establece el tamaño del JFrame
        setSize(500, 500);
//        Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
//        frame.setSize(size);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Se elimina la "decoración" del JFrame
        setUndecorated(true);

        /**
         * Una vez liminada la decoración, se establece la Opcaidad
         *
         * Recibe un parámetro "float"
         */
//        setOpacity(slider.getValue() * 0.01f);
        //Se establece la ubicación del JFrame
        setLocationRelativeTo(null);

        setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        label.setText("Nivel de tranparencia : " + (100 - slider.getValue()) + "%");
        double result = slider.getValue();
        setBackground(new Color(defaultBackground.getRed(), defaultBackground.getGreen(),
                defaultBackground.getBlue(), (int) result));

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                Recorder frame;
                try {
                    frame = new Recorder();
                    frame.setVisible(true);
                } catch (AWTException ex) {
                }

            }
        });
    }

    //
    private final JSlider slider;

    // JPanel
    private final JPanel panel;

    // JLabel
    private final JLabel label;

    //
    private final JButton boton1;

    //
    private final Color defaultBackground;

    //
//    private final Accion act;

}
