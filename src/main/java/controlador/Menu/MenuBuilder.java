/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Menu;

/**
 *
 * @author gzapata
 */
public abstract class MenuBuilder {
    protected Menu menu;

    public Menu getMenu() {
        return menu;
    }

    public void crearMenu() {
        this.menu = new Menu();
    }
    
    public abstract void buildTitulo();
    public abstract void buildSubMenus();
}
