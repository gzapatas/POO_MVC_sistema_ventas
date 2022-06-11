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
public class OrdenDetallesDAO extends Conexion {
    public ArrayList<OrdenDetalles> listar(){
        Connection con = getConnection();
        String query = "SELECT idDetalle,idOrden,idProducto,cantidad,precioUnitario,"
                + "precioTotal,fecha,fechaHora,timestamp"
                + " FROM OrdenDetalles";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<OrdenDetalles> list = new ArrayList<OrdenDetalles>();
            
            while(rs.next()) {
                OrdenDetalles item = new OrdenDetalles();
                
                item.setIdDetalle(Long.parseLong(rs.getString("idDetalle")));
                item.setIdOrden(Long.parseLong(rs.getString("idOrden")));
                item.setIdProducto(Long.parseLong(rs.getString("idProducto")));
                item.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                item.setPrecioUnitario(Double.parseDouble(rs.getString("precioUnitario")));
                item.setPrecioTotal(Double.parseDouble(rs.getString("precioTotal")));
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
    
    public OrdenDetalles buscar(long id){
        Connection con = getConnection();
        String query = "SELECT idDetalle,idOrden,idProducto,cantidad,precioUnitario,"
                + "precioTotal,fecha,fechaHora,timestamp FROM OrdenDetalles"
                + " WHERE idDetalle = ? LIMIT 1";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            OrdenDetalles item = new OrdenDetalles();
            
            while(rs.next()) {                
                item.setIdDetalle(Long.parseLong(rs.getString("idDetalle")));
                item.setIdOrden(Long.parseLong(rs.getString("idOrden")));
                item.setIdProducto(Long.parseLong(rs.getString("idProducto")));
                item.setCantidad(Integer.parseInt(rs.getString("cantidad")));
                item.setPrecioUnitario(Double.parseDouble(rs.getString("precioUnitario")));
                item.setPrecioTotal(Double.parseDouble(rs.getString("precioTotal")));
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
    
    public boolean insertar(OrdenDetalles item){
        Connection con = getConnection();
        String query = "INSERT INTO OrdenDetalles(idOrden,idProducto,cantidad,"
                + "precioUnitario,precioTotal,fecha,fechaHora,timestamp"
                + " VALUES(?,?,?,?,?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setLong(i++,item.getIdOrden());
            ps.setLong(i++,item.getIdProducto());
            ps.setInt(i++,item.getCantidad());
            ps.setDouble(i++,item.getPrecioUnitario());
            ps.setDouble(i++,item.getPrecioTotal());
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
    
    public boolean actualizar(OrdenDetalles item){
        Connection con = getConnection();
        String query = "UPDATE OrdenDetalles SET idOrden = ?,idProducto = ?,"
                + "cantidad = ?,precioUnitario = ?,precioTotal = ?,"
                + "fecha = ?,fechaHora = ?,timestamp = ?"
                + " WHERE idDetalle = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setLong(i++,item.getIdOrden());
            ps.setLong(i++,item.getIdProducto());
            ps.setInt(i++,item.getCantidad());
            ps.setDouble(i++,item.getPrecioUnitario());
            ps.setDouble(i++,item.getPrecioTotal());
            ps.setDate(i++,item.getFecha());
            ps.setDate(i++,item.getFechaHora());
            ps.setLong(i++,item.getTimestamp());
            ps.setLong(i++,item.getIdDetalle());
            
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
        String query = "DELETE FROM OrdenDetalles WHERE idDetalle = ?";
        
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
