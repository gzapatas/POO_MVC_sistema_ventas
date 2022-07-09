/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Menu;

import controlador.CtrlProductos;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import vista.ProductosView;

/**
 *
 * @author gzapata
 */
public class MenuItemProductos implements MenuItem {
    private final CtrlProductos controller;
    private final JMenuItem item;

    public MenuItemProductos(String text) {
        this.item = new JMenuItem(text);
        ProductosView view = new ProductosView();
        controller = new CtrlProductos(view);
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
