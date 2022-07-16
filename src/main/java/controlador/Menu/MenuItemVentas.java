/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Menu;

import controlador.CtrlVentas;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import vista.VentasView;

/**
 *
 * @author gzapata
 */
public class MenuItemVentas implements MenuItem {
    private final CtrlVentas controller;
    private final JMenuItem item;

    public MenuItemVentas(String text) {
        this.item = new JMenuItem(text);
        VentasView view = new VentasView();
        controller = new CtrlVentas(view);
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
