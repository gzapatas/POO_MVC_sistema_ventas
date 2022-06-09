package database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gzapata
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String name = "LanCardsDB";
    private final String user = "root";
    private final String password = "Kr@krKr3kr";
    private final String url = "jdbc:mysql:// localhost:3306/" + name;
    private Connection con  = null;
    
    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(this.url, this.user, this.password); 
        } catch(SQLException ex) {
            System.err.println(ex);
        }
        return con;
    }
}
