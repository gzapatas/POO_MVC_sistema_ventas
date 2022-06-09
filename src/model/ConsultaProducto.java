/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author gzapata
 */

import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultaProducto extends Conexion {
    public ArrayList<Producto> Buscar(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConnection();
        
        String query = "SELECT * FROM Cards";
        
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<Producto> list = new ArrayList<Producto>();
            
            while(rs.next()) {
                Producto prod = new Producto();
                
                prod.setCardId(rs.getString("cardId"));
                prod.setAccountId(Integer.parseInt(rs.getString("accountId")));
                prod.setStatus(Integer.parseInt(rs.getString("status")));
                prod.setRegUserId(Integer.parseInt(rs.getString("regUserId")));
                prod.setRegDate(rs.getString("regDate"));
                prod.setRegDatetime(rs.getString("regDatetime"));
                prod.setRegTimestamp(Integer.parseInt(rs.getString("regUserId")));
                
                list.add(prod);
            }
            
            return list;
        } catch(SQLException ex) {
            System.err.println(ex);
            return null;
        }
        finally {
            try {
                con.close();
            } catch(SQLException ex) {
                System.err.println(ex);
            }
            
        }
    }
    
    public boolean Crear(Producto prod){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConnection();
        
        String query = "SELECT * FROM Cards";
        
        try {
            ps = con.prepareStatement(query);
            ps.execute();
            return true;
        } catch(SQLException ex) {
            System.err.println(ex);
            return false;
        }
        finally {
            try {
                con.close();
            } catch(SQLException ex) {
                System.err.println(ex);
            }
            
        }
    }
}
