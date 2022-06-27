/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.Menu;

/**
 *
 * @author gzapata
 */
public class MenuDirector {
    private MenuBuilder builder;
    
    public void setMenuBuilder(MenuBuilder builder){
        this.builder = builder;
    }
    
    public Menu getMenu() {
        return builder.getMenu();
    }
    
    public void iniciarMenu() {
        builder.crearMenu();
        builder.buildTitulo();
        builder.buildSubMenus();
    }
}
