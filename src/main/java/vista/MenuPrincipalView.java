/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
import java.awt.Component;
import javax.swing.JInternalFrame;

/**
 *
 * @author gzapata
 */
public class MenuPrincipalView extends PrincipalView {
    private static MenuPrincipalView instance;
    
    public static MenuPrincipalView getInstance(){
        if(instance == null){
            instance = new MenuPrincipalView();
        }
        return instance;
    }
    
    public void AddWindow(Component view){
        JInternalFrame internal = new JInternalFrame();
        internal.add(view);
        internal.pack();
        internal.setResizable(true);
        internal.setClosable(true);
        internal.setVisible(true);
        desktop.add(internal);
    }
}
