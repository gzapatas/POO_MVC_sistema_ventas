/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.db;

import java.sql.Connection;

/**
 *
 * @author gzapata
 */
public abstract class ConexionFactory {
    public static String MYSQL = "Mysql";
    
    public static void initializeDbDriver(String key){
        switch(key){
            case "Mysql":
                new Mysql().initialize();
        }
    }
    
    
    public static Connection getConnection(String key){
        ConexionInterface item = null;
        
        switch(key){
            case "Mysql":
                return new Mysql().getConnection();
            default:
                return null;
        }
    }
}
