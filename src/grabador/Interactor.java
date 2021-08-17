/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grabador;

import java.io.IOException;
import erel.Compiler.ErelWriter;
import erel.Compiler.ErelLoader;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.Calendar;

/**
 *
 * @author Juan Pablo - Roverin Technologics
 */
public class Interactor /*implements MouseListener, KeyListener*/ {

    /**
     * 
     */
    public Interactor(){        
    }
    /**
     *
     * @param NombreMacro
     * @param PROCESS
     * @throws IOException
     * @throws AWTException
     */
    public Interactor(String NombreMacro, int PROCESS) throws IOException, AWTException {
        if (NombreMacro != null) {
            switch (PROCESS) {
                case 101:
            this.ew = new ErelWriter(modify("/dimRest/dll-exec-x68-" + formatString(Calendar.getInstance().getTime().toString(), 1) + formatString(NombreMacro, 1) + ".erel"));
                    this.er = null;
            new Thread(this.ew).start();
                    break;
                case 102:
                    this.ew = null;
            this.er = new ErelLoader(modify("/dimRest/dll-exec-x68-" + NombreMacro + ".erel"), new Robot());
                    break;
                default:
                    throw new IOException(" ¡ El proceso a realizar no se encuentra !");
            }
        } else {
            throw new IOException(" ¡ El nombre del archivo es nulo !");
        }
    }

//    /**
//     *
//     * @param ch
//     * @param Command
//     */
//    public void grabar(int ch, String Command) {
//        switch (ch) {
//            case 1:
//                ew.addClickEvent(Command, ch, ch, ch);
//                break;
//            case 2:
//                
//                break;
//        }
//    }
    /**
     *
     * @return @throws IOException
     */
    public String generarInstrucccion() throws IOException {
        ew.writeFile();
        return "cmd add correctly";
    }

    /**
     *
     * @param Value
     * @param Option
     * @return
     */
    private String formatString(String Value, int Option) {
        if (Option == 1 || Option == 2) {
            String val = Value;
            String[] ch = new String[val.length()];
            for (int index = 0; index < Value.length(); index++) {
                int p = index;
                if (p == Value.length()) {
                    ch[index] = val.substring(p);
                } else {
                    ch[index] = val.substring(p, p + 1);
                }
            }
            if (Option == 1) {
                for (int index = 0; index < Value.length(); index++) {
                    switch (ch[index]) {
                        case " ":
                            ch[index] = "_";
                            break;
                        case "/":
                            ch[index] = "-";
                            break;
                        case ":" , "*",  "?" , "<", ">", "|":
                            ch[index] = ".";
                            break;
                    }
                }
            } else if (Option == 2) {
                for (int index = 0; index < Value.length(); index++) {
                    switch (ch[index]) {
                        case "_":
                            ch[index] = " ";
                            break;
                        case "-":
                            ch[index] = " / ";
                            break;
                        case "&":
                            ch[index] = " -> ";
                            break;
                    }
                }
            }
            String result = String.join("", ch);
            return result;
        }
        return " ¡ Error de formateo !";
    }

    /**
     * 
     * @param filename
     * @param un
     * @return 
     */
    public String formatName(String filename, int un) {
        switch (un) {
            case 1:
                String pal = formatString(filename, FROM_FORMAT);
                int larg = pal.length();
                return pal.substring(19, 47) + " -> " + pal.substring(47, larg - 5);
            case 2:
                return formatString(filename.substring(0, 28) + filename.substring(32), TO_FORMAT);
        }
        return null;
    }
    
    /**
     * 
     * @param ref
     * @param dic
     * @return 
     */
    public String modify(String dic) {
        
        String aux = getClass().getResource("/display").getFile().substring(1, 66) + dic;
        
        StringBuffer hud = new StringBuffer();

        for (int i = 0; i < aux.length(); i++) {
            String tas = aux.substring(i, i + 1);
            switch (tas) {
                case "/":
                    hud.append("\\");
                    break;
                case "%":
                    hud.append(" ");
                    i++;
                    i++;
                    break;
                default:
                    hud.append(tas);
                    break;
            }
        }
        return hud.toString();
    }

    /**
     *
     */
    public void leerInstruccion() {
        er.readFile();
    }
    
    /**
     * 
     * @return 
     */
    public int tiempoEjec(){
        return er.getTimeOfSteps();
    } 

    //
    public int TO_FORMAT = 1;
    public int FROM_FORMAT = 2;
    public static int WRITE_EREL_FILE = 101;
    public static int READ_EREL_FILE = 102;
    public ErelWriter ew;
    public ErelLoader er;
}
