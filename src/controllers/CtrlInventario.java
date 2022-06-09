/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ConsultaProducto;
import model.Producto;
import view.Inventario;

/**
 *
 * @author gzapata
 */
public class CtrlInventario implements ActionListener {
    private Inventario view;
    private ConsultaProducto consulta;

    public CtrlInventario() {
        this.view = new Inventario();
        this.consulta = new ConsultaProducto();
        this.view.btnMultiplicar.addActionListener(this);
        this.view.setVisible(false);
    }
    
    public void init() {
        this.view.setTitle("Inventario");
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);
    }
    
    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(consulta.Buscar());
    }
    
}
