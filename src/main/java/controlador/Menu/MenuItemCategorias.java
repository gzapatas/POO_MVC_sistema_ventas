/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Menu;

import controlador.CtrlCategorias;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import vista.CategoriasView;

/**
 *
 * @author gzapata
 */
public class MenuItemCategorias implements MenuItem {
    private final CtrlCategorias controller;
    private final JMenuItem item;

    public MenuItemCategorias(String text) {
        this.item = new JMenuItem(text);
        CategoriasView view = new CategoriasView();
        controller = new CtrlCategorias(view);
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
