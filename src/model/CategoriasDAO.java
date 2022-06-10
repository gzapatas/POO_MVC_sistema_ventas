package model;

import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gzapata
 */
public class CategoriasDAO extends Conexion {
    public ArrayList<Categorias> listar(){
        Connection con = getConnection();
        String query = "SELECT idCategoria,nombre,fecha,fechaHora,timestamp"
                + " FROM Categorias";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Categorias> list = new ArrayList<Categorias>();
            
            while(rs.next()) {
                Categorias item = new Categorias();
                
                item.setIdCategoria(Long.parseLong(rs.getString("idCategoria")));
                item.setNombre(rs.getString("nombre"));
                item.setFecha(rs.getDate("fecha"));
                item.setFechaHora(rs.getDate("fechaHora"));
                item.setTimestamp(Long.parseLong(rs.getString("timestamp")));
                
                list.add(item);
            }
            
            ps.close();
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
    
    public Categorias buscar(long id){
        Connection con = getConnection();
        String query = "SELECT idCategoria,nombre,fecha,fechaHora,timestamp"
                + " FROM Categorias WHERE idCategoria = ? LIMIT 1";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            Categorias item = new Categorias();
            
            while(rs.next()) {                
                item.setIdCategoria(Long.parseLong(rs.getString("idCategoria")));
                item.setNombre(rs.getString("nombre"));
                item.setFecha(rs.getDate("fecha"));
                item.setFechaHora(rs.getDate("fechaHora"));
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
    
    public boolean insertar(Categorias item){
        Connection con = getConnection();
        String query = "INSERT INTO Categorias(nombre,fecha,fechaHora,timestamp"
                + " VALUES(?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setString(i++,item.getNombre());
            ps.setDate(i++,item.getFecha());
            ps.setDate(i++,item.getFechaHora());
            ps.setLong(i++,item.getTimestamp());
            
            ps.execute();
            ps.close();
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
    
    public boolean actualizar(Categorias item){
        Connection con = getConnection();
        String query = "UPDATE Categorias SET nombre = ?,fecha = ?,"
                + "fechaHora = ?,timestamp = ? WHERE idCategoria = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setString(i++,item.getNombre());
            ps.setDate(i++,item.getFecha());
            ps.setDate(i++,item.getFechaHora());
            ps.setLong(i++,item.getTimestamp());
            ps.setLong(i++,item.getIdCategoria());
            
            ps.executeUpdate();
            ps.close();
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
    
    public boolean eliminar(long id){
        Connection con = getConnection();
        String query = "DELETE FROM Categorias WHERE idCategoria = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1,id);
            
            ps.executeUpdate();
            ps.close();
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
