/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.Menu.MenuBuilderMantenedores;
import controllers.Menu.MenuBuilderVentas;
import controllers.Menu.MenuDirector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MenuPrincipalView;
import view.PrincipalView;

/**
 *
 * @author gzapata
 */
public class CtrlPrincipal implements ActionListener {
    private final PrincipalView view;

    public CtrlPrincipal(PrincipalView view) {
        this.view = view;
        
        MenuBuilderMantenedores inventario = new MenuBuilderMantenedores();
        MenuBuilderVentas ventas = new MenuBuilderVentas();
        MenuDirector director = new MenuDirector();
        director.setMenuBuilder(inventario);
        director.iniciarMenu();
        this.view.menubar.add(director.getMenu().getJMenu());
        director.setMenuBuilder(ventas);
        director.iniciarMenu();
        this.view.menubar.add(director.getMenu().getJMenu());
        this.view.setVisible(false);
    }
    
    public void init() {
        this.view.setTitle("Sistema de ventas");
        this.view.setLocationRelativeTo(null);
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