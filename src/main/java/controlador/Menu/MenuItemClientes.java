/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Menu;

import controlador.CtrlClientes;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import vista.ClientesView;

/**
 *
 * @author gzapata
 */
public class MenuItemClientes implements MenuItem {
    private final CtrlClientes controller;
    private final JMenuItem item;

    public MenuItemClientes(String text) {
        this.item = new JMenuItem(text);
        ClientesView view = new ClientesView();
        controller = new CtrlClientes(view);
        this.item.addActionListener(this);
    }

    @Override
    public JMenuItem getItem() {
        return item;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        controller.init();
    }
    
}
