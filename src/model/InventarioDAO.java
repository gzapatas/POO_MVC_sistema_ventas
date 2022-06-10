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
public class InventarioDAO extends Conexion {
    public ArrayList<Inventario> listar(){
        Connection con = getConnection();
        String query = "SELECT idInventario,idProducto,cantidad,precioUnitario,"
                + "fecha,fechaHora,timestamp"
                + " FROM Inventario";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Inventario> list = new ArrayList<Inventario>();
            
            while(rs.next()) {
                Inventario item = new Inventario();
                
                item.setIdInventario(Long.parseLong(rs.getString("idInventario")));
                item.setIdProducto(Long.parseLong(rs.getString("idProducto")));
                item.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                item.setPrecioUnitario(Double.parseDouble(rs.getString("precioUnitario")));
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
    
    public Inventario buscar(long id){
        Connection con = getConnection();
        String query = "SELECT idInventario,idProducto,cantidad,precioUnitario,"
                + "fecha,fechaHora,timestamp FROM Inventario"
                + " WHERE idInventario = ? LIMIT 1";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            Inventario item = new Inventario();
            
            while(rs.next()) {                
                item.setIdInventario(Long.parseLong(rs.getString("idInventario")));
                item.setIdProducto(Long.parseLong(rs.getString("idProducto")));
                item.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                item.setPrecioUnitario(Double.parseDouble(rs.getString("precioUnitario")));
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
    
    public boolean insertar(Inventario item){
        Connection con = getConnection();
        String query = "INSERT INTO Inventario(idProducto,cantidad,precioUnitario,"
                + "fecha,fechaHora,timestamp"
                + " VALUES(?,?,?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setLong(i++,item.getIdProducto());
            ps.setInt(i++,item.getCantidad());
            ps.setDouble(i++,item.getPrecioUnitario());
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
    
    public boolean actualizar(Inventario item){
        Connection con = getConnection();
        String query = "UPDATE Inventario SET idProducto = ?,cantidad = ?,"
                + "precioUnitario = ?,fecha = ?,fechaHora = ?,timestamp = ?"
                + " WHERE idInventario = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setLong(i++,item.getIdProducto());
            ps.setInt(i++,item.getCantidad());
            ps.setDouble(i++,item.getPrecioUnitario());
            ps.setDate(i++,item.getFecha());
            ps.setDate(i++,item.getFechaHora());
            ps.setLong(i++,item.getTimestamp());
            ps.setLong(i++,item.getIdInventario());
            
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
        String query = "DELETE FROM Inventario WHERE idInventario = ?";
        
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
