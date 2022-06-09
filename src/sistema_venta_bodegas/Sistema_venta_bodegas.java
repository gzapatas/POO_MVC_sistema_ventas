/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_venta_bodegas;
import controllers.CtrlInventario;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gzapata
 */
public class Sistema_venta_bodegas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch(ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Sistema_venta_bodegas.class.getName()).log(Level.SEVERE, null, ex);
        }
        CtrlInventario ctrl = new CtrlInventario();
        ctrl.init();
    }
    
}
