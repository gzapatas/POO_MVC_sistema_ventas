/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Menu;

import java.util.ArrayList;
import javax.swing.JMenuItem;

/**
 *
 * @author gzapata
 */
public class MenuBuilderAyuda extends MenuBuilder {

    @Override
    public void buildTitulo() {
        menu.setTitulo("Ayuda"); 
   }

    @Override
    public void buildSubMenus() {
        ArrayList<JMenuItem> menuItems = new ArrayList<>();
        
        menuItems.add(MenuItemFactory.getMenuItem(MenuItemFactory.ACERCA).getItem());
        
        menu.setSubMenus(menuItems);
    }
    
}
