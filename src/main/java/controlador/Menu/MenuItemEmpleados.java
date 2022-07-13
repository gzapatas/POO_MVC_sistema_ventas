/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Menu;

import controlador.CtrlEmpleados;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import vista.EmpleadosView;

/**
 *
 * @author gzapata
 */
public class MenuItemEmpleados implements MenuItem {
    private final CtrlEmpleados controller;
    private final JMenuItem item;

    public MenuItemEmpleados(String text) {
        this.item = new JMenuItem(text);
        EmpleadosView view = new EmpleadosView();
        controller = new CtrlEmpleados(view);
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
