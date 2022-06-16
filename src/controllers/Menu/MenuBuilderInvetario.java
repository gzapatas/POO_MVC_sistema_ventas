/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Menu;

import java.util.ArrayList;
import javax.swing.JMenuItem;

/**
 *
 * @author gzapata
 */
public class MenuBuilderInvetario extends MenuBuilder {

    @Override
    public void buildTitulo() {
        menu.setTitulo("Inventario");
    }

    @Override
    public void buildSubMenus() {
        ArrayList<JMenuItem> menuItems = new ArrayList<>();

        menuItems.add(MenuItemFactory.getMenuItem(MenuItemFactory.INVENTARIO).getItem());
        menuItems.add(MenuItemFactory.getMenuItem(MenuItemFactory.PRODUCTOS).getItem());
        menuItems.add(MenuItemFactory.getMenuItem(MenuItemFactory.CATEGORIAS).getItem());
        
        menu.setSubMenus(menuItems);
    }
    
}
