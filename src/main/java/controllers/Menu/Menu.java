/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Menu;

import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author gzapata
 */
public class Menu {
    private final JMenu menu;
    
    public Menu(){
        this.menu = new JMenu();
    }
    
    public JMenu getJMenu(){
        return menu;
    }
    
    public void setSubMenus(ArrayList<JMenuItem> menuItems) {
        this.menu.removeAll();
        
        menuItems.forEach(menuItem -> {
            this.menu.add(menuItem);
        });
    }

    public void setTitulo(String title) {
        this.menu.setText(title);
    }
}
