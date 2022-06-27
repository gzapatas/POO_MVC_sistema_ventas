/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_venta_bodegas;
import configs.Env;
import controllers.CtrlPrincipal;
import database.ConexionFactory;
import view.MenuPrincipalView;

/**
 *
 * @author gzapata
 */
public class Sistema_venta_bodegas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Env.getInstance().InitEnv();
        ConexionFactory.initializeDbDriver(ConexionFactory.MYSQL);
        
        CtrlPrincipal ctrl = new CtrlPrincipal(MenuPrincipalView.getInstance());
        ctrl.init();
    }
    
}
