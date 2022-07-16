package modelo.dao;

import modelo.db.ConexionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.db.Inventario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gzapata
 */
public class InventarioDAO extends ConexionFactory {
    public ArrayList<Inventario> listar(){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "SELECT i.idInventario,i.idProducto,i.cantidad"
                + ",i.precioUnitario,i.fecha,i.fechaHora,i.timestamp, p.nombre "
                + "FROM Inventario i INNER JOIN Productos p "
                + "ON (i.idProducto = p.idProducto)";
        
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
                item.setFecha(rs.getString("fecha"));
                item.setFechaHora(rs.getString("fechaHora"));
                item.setTimestamp(Long.parseLong(rs.getString("timestamp")));
                item.setProducto(rs.getString("nombre"));
                
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
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "SELECT i.idInventario,i.idProducto,i.cantidad"
                + ",i.precioUnitario,i.fecha,i.fechaHora,i.timestamp, p.nombre "
                + "FROM Inventario i INNER JOIN Productos p "
                + "ON (i.idProducto = p.idProducto) "
                + "WHERE i.idInventario = ? LIMIT 1";
        
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
                item.setFecha(rs.getString("fecha"));
                item.setFechaHora(rs.getString("fechaHora"));
                item.setTimestamp(Long.parseLong(rs.getString("timestamp")));
                item.setProducto(rs.getString("nombre"));
                
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
    
    public Inventario buscarPorProducto(long id){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "SELECT i.idInventario,i.idProducto,i.cantidad"
                + ",i.precioUnitario,i.fecha,i.fechaHora,i.timestamp, p.nombre "
                + "FROM Inventario i INNER JOIN Productos p "
                + "ON (i.idProducto = p.idProducto) "
                + "WHERE i.idProducto = ? LIMIT 1";
        
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
                item.setFecha(rs.getString("fecha"));
                item.setFechaHora(rs.getString("fechaHora"));
                item.setTimestamp(Long.parseLong(rs.getString("timestamp")));
                item.setProducto(rs.getString("nombre"));
                
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
    
    public boolean sp_insertarinventario(Inventario item){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "CALL sp_insertarinventario(?,?,?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setLong(i++,item.getIdProducto());
            ps.setInt(i++,item.getCantidad());
            ps.setDouble(i++,item.getPrecioUnitario());
            ps.setString(i++,item.getFecha());
            ps.setString(i++,item.getFechaHora());
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
    
    public boolean sp_editarinventario(Inventario item){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "CALL sp_editarinventario(?,?,?,?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setLong(i++,item.getIdInventario());
            ps.setLong(i++,item.getIdProducto());
            ps.setInt(i++,item.getCantidad());
            ps.setDouble(i++,item.getPrecioUnitario());
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
    
    public boolean reducirInventario(Inventario item){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "UPDATE Inventario SET cantidad = cantidad - ? WHERE idInventario = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            ps.setInt(i++,item.getCantidad());
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
    
    public boolean sp_eliminarinventario(long id){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "CALL sp_eliminarinventario(?);";
        
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
