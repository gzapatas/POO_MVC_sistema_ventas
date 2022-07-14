package modelo.dao;

import modelo.db.ConexionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.db.Categorias;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gzapata
 */
public class CategoriasDAO extends ConexionFactory {
    public ArrayList<Categorias> listar(){
        Connection con = getConnection(ConexionFactory.MYSQL);
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
                item.setFecha(rs.getString("fecha"));
                item.setFechaHora(rs.getString("fechaHora"));
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
        Connection con = getConnection(ConexionFactory.MYSQL);
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
    
    public boolean sp_insertarcategoria(Categorias item){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "CALL sp_insertarcategoria(?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setString(i++,item.getNombre());
            ps.setString(i++,item.getFecha());
            ps.setString(i++,item.getFechaHora());
            ps.setLong(i++,item.getTimestamp());
            
            System.out.println(ps.toString());
            
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
    
    public boolean sp_editarcategoria(Categorias item){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "CALL sp_editarcategoria(?,?,?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setLong(i++,item.getIdCategoria());
            ps.setString(i++,item.getNombre());
            ps.setString(i++,item.getFecha());
            ps.setString(i++,item.getFechaHora());
            ps.setLong(i++,item.getTimestamp());
            
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
    
    public boolean sp_eliminarcategoria(long id){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "CALL sp_eliminarcategoria(?)";
        
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
