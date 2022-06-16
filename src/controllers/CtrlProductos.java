/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import view.MenuPrincipal;
import view.Productos;

/**
 *
 * @author gzapata
 */
public class CtrlProductos implements ActionListener {
    private final Productos view;

    public CtrlProductos(Productos view) {
        this.view = view;
        this.view.btnAgregar.addActionListener(this);
        this.view.btnBuscar.addActionListener(this);
    }
    
    public void init() {
        JInternalFrame internal = new JInternalFrame();
        internal.add(this.view);
        internal.pack();
        internal.setResizable(true);
        internal.setClosable(true);
        internal.setVisible(true);
        MenuPrincipal.getInstance().desktop.add(internal);
        this.view.setVisible(true);
    }
    
    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(consulta.Buscar());
    }
}
