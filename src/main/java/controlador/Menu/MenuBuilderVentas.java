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
public class MenuBuilderVentas extends MenuBuilder {

    @Override
    public void buildTitulo() {
        menu.setTitulo("Ventas"); 
   }

    @Override
    public void buildSubMenus() {
        ArrayList<JMenuItem> menuItems = new ArrayList<>();
        
        menuItems.add(MenuItemFactory.getMenuItem(MenuItemFactory.VENTAS).getItem());
        
        menu.setSubMenus(menuItems);
    }
    
}
