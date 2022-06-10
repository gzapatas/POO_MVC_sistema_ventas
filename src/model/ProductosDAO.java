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
public class ProductosDAO extends Conexion {
    public ArrayList<Productos> listar(){
        Connection con = getConnection();
        String query = "SELECT idProducto,sku,nombre,idCategoria,descripcion,"
                + "fecha,fechaHora,timestamp"
                + " FROM Productos";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Productos> list = new ArrayList<Productos>();
            
            while(rs.next()) {
                Productos item = new Productos();
                
                item.setIdProducto(Long.parseLong(rs.getString("idProducto")));
                item.setSku(rs.getString("sku"));
                item.setNombre(rs.getString("nombre"));
                item.setIdCategoria(Long.parseLong(rs.getString("idCategoria")));
                item.setDescripcion(rs.getString("descripcion"));
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
    
    public Productos buscar(long id){
        Connection con = getConnection();
        String query = "SELECT idProducto,sku,nombre,idCategoria,descripcion,"
                + "fecha,fechaHora,timestamp FROM Productos"
                + " WHERE idProducto = ? LIMIT 1";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            Productos item = new Productos();
            
            while(rs.next()) {                
                item.setIdProducto(Long.parseLong(rs.getString("idProducto")));
                item.setSku(rs.getString("sku"));
                item.setNombre(rs.getString("nombre"));
                item.setIdCategoria(Long.parseLong(rs.getString("idCategoria")));
                item.setDescripcion(rs.getString("descripcion"));
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
    
    public boolean insertar(Productos item){
        Connection con = getConnection();
        String query = "INSERT INTO Productos(sku,nombre,idCategoria,descripcion,"
                + "fecha,fechaHora,timestamp"
                + " VALUES(?,?,?,?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setString(i++,item.getSku());
            ps.setString(i++,item.getNombre());
            ps.setLong(i++,item.getIdCategoria());
            ps.setString(i++,item.getDescripcion());
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
    
    public boolean actualizar(Productos item){
        Connection con = getConnection();
        String query = "UPDATE Productos SET sku = ?,nombre = ?,"
                + "idCategoria = ?,descripcion = ?,"
                + "fecha = ?,fechaHora = ?,timestamp = ?"
                + " WHERE idProducto = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setString(i++,item.getSku());
            ps.setString(i++,item.getNombre());
            ps.setLong(i++,item.getIdCategoria());
            ps.setString(i++,item.getDescripcion());
            ps.setDate(i++,item.getFecha());
            ps.setDate(i++,item.getFechaHora());
            ps.setLong(i++,item.getTimestamp());
            ps.setLong(i++,item.getIdProducto());
            
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
        String query = "DELETE FROM Productos WHERE idDetalle = ?";
        
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
