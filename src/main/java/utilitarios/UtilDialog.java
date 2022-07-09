/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author gzapata
 */
public class UtilDialog {
    public static void Error(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void Information(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Informacion", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void Warning(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Alerta", JOptionPane.WARNING_MESSAGE);
    }
    
    public static boolean Question(Component parent, String message){
        var response = JOptionPane.showConfirmDialog(parent, message, "Pregunta", JOptionPane.YES_NO_OPTION);
        
        return response != JOptionPane.NO_OPTION;
    }
}
