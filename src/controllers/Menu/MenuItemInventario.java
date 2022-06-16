/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Menu;

import controllers.CtrlInventario;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import view.Inventario;

/**
 *
 * @author gzapata
 */
public class MenuItemInventario implements MenuItem {
    private final CtrlInventario controller;
    private final JMenuItem item;

    public MenuItemInventario(String text) {
        this.item = new JMenuItem(text);
        Inventario view = new Inventario();
        controller = new CtrlInventario(view);
        this.item.addActionListener(this);
    }

    public JMenuItem getItem() {
        return item;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        controller.init();
    }
    
}
