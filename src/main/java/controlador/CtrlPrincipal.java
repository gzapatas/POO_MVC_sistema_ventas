/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.Menu.MenuBuilderAyuda;
import controlador.Menu.MenuBuilderMantenedores;
import controlador.Menu.MenuBuilderVentas;
import controlador.Menu.MenuDirector;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import vista.PrincipalView;

/**
 *
 * @author gzapata
 */
public class CtrlPrincipal extends AbstractAction {
    private final PrincipalView view;

    public CtrlPrincipal(PrincipalView view) {
        this.view = view;
        
        MenuBuilderMantenedores inventario = new MenuBuilderMantenedores();
        MenuBuilderVentas ventas = new MenuBuilderVentas();
        MenuBuilderAyuda ayuda = new MenuBuilderAyuda();
        MenuDirector director = new MenuDirector();
        
        director.setMenuBuilder(inventario);
        director.iniciarMenu();
        this.view.menubar.add(director.getMenu().getJMenu());
        
        director.setMenuBuilder(ventas);
        director.iniciarMenu();
        this.view.menubar.add(director.getMenu().getJMenu());
        this.view.setVisible(false);
        
        director.setMenuBuilder(ayuda);
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