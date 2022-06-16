/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import view.Principal;

/**
 *
 * @author gzapata
 */
public class MenuPrincipal extends Principal {
    private static MenuPrincipal instance;
    
    public static MenuPrincipal getInstance(){
        if(instance == null){
            instance = new MenuPrincipal();
        }
        return instance;
    }
}
