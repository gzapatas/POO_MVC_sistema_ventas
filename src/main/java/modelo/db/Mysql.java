/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.db;

import configuraciones.Env;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema_venta_bodegas.Sistema_venta_bodegas;

/**
 *
 * @author gzapata
 */
public class Mysql implements ConexionInterface {
    private Connection con  = null;
    
    @Override
    public void initialize() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch(ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Sistema_venta_bodegas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Connection getConnection() {
        try {
            Env env = Env.getInstance();
            String url = "jdbc:mysql://" 
                + env.DbModel.getIp() + ":" 
                + env.DbModel.getPort()+ "/" 
                + env.DbModel.getName();

            con = DriverManager.getConnection(url, env.DbModel.getUser(), env.DbModel.getPassword()); 
        } catch(SQLException ex) {
            System.err.println(ex);
        }
        return con;
    }
}
