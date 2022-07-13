/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Menu;

import controlador.CtrlClientes;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import utilitarios.UtilDialog;
import vista.ClientesView;

/**
 *
 * @author gzapata
 */
public class MenuItemAcerca implements MenuItem {
    private final JMenuItem item;

    public MenuItemAcerca(String text) {
        this.item = new JMenuItem(text);
        this.item.addActionListener(this);
    }

    @Override
    public JMenuItem getItem() {
        return item;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        UtilDialog.Information(null, "Sistema de ventas 1.0");
    }
    
}
