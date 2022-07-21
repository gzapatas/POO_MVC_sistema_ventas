package modelo.dao;

import modelo.db.ConexionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.db.Usuarios;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gzapata
 */
public class UsuariosDAO extends ConexionFactory {
    public Usuarios sp_login(String username, String password){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "CALL sp_login(?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            
            ResultSet rs = ps.executeQuery();
            Usuarios item = new Usuarios();
            
            while(rs.next()) {                
                item.setIdUsuario(Long.parseLong(rs.getString("idUsuario")));
                item.setUsername(rs.getString("username"));
                item.setFecha(rs.getString("fecha"));
                item.setFechaHora(rs.getString("fechaHora"));
                item.setTimestamp(Long.parseLong(rs.getString("timestamp")));
                
                break;
            }
            
            ps.close();
            return item;
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
}
