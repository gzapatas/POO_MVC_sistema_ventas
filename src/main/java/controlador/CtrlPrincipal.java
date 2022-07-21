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
import modelo.dao.UsuariosDAO;
import utilitarios.UtilDialog;
import vista.LoginView;
import vista.PrincipalView;

/**
 *
 * @author gzapata
 */
public class CtrlPrincipal extends AbstractAction {
    private final PrincipalView view;
    private final LoginView loginView;
    private final UsuariosDAO usuariosDAO;

    public CtrlPrincipal(PrincipalView view) {
        this.view = view;
        this.loginView = new LoginView();
        this.usuariosDAO = new UsuariosDAO();
        
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
        
        this.loginView.btnIniciar.addActionListener(this);
        this.loginView.btnIniciar.setActionCommand("LOGIN");
        this.view.btnCerrar.addActionListener(this);
        this.view.btnCerrar.setActionCommand("LOGOUT");
    }
    
    public void init() {
        this.view.setTitle("Sistema de ventas");
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(false);
        this.loginView.setLocationRelativeTo(null);
        this.loginView.setVisible(true);
    }
    
    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        var command = e.getActionCommand();
        var listcmd = command.split("_");

        switch(listcmd[0]){
            case "LOGIN": {
                this.login();
                break;
            }
            case "LOGOUT": {
                this.logout();
                break;
            }
        }
    }
    
    private void login(){
        String username = loginView.usuarioText.getText();
        String password = String.valueOf(loginView.passwordText.getPassword());
    
        var user = usuariosDAO.sp_login(username, password);
        
        if(user.getIdUsuario() == 0){
            UtilDialog.Error(view, "Usuario y/o contraseña incorrecta");
            return;
        }
        
        this.view.lblBienvenido.setText("Bienvenido, " + user.getUsername());
        this.loginView.setVisible(false);
        this.view.setVisible(true);
        this.loginView.usuarioText.setText("");
        this.loginView.passwordText.setText("");
    }
    
    private void logout(){
        this.loginView.setVisible(true);
        this.view.setVisible(false);
    }
    
}