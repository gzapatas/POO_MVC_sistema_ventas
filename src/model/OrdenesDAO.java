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
public class OrdenesDAO extends Conexion {
    public ArrayList<Ordenes> listar(){
        Connection con = getConnection();
        String query = "SELECT idOrden,idCliente,idCajero,montoTotal,"
                + "descuento,fecha,fechaHora,timestamp"
                + " FROM Ordenes";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Ordenes> list = new ArrayList<Ordenes>();
            
            while(rs.next()) {
                Ordenes item = new Ordenes();
                
                item.setIdOrden(Long.parseLong(rs.getString("idOrden")));
                item.setIdCliente(Long.parseLong(rs.getString("idCliente")));
                item.setIdCajero(Long.parseLong(rs.getString("idCajero")));
                item.setMontoTotal(Double.parseDouble(rs.getString("montoTotal")));
                item.setDescuento(Double.parseDouble(rs.getString("descuento")));
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
    
    public Ordenes buscar(long id){
        Connection con = getConnection();
        String query = "SELECT idOrden,idCliente,idCajero,montoTotal,"
                + "descuento,fecha,fechaHora,timestamp FROM Ordenes"
                + " WHERE idOrden = ? LIMIT 1";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            Ordenes item = new Ordenes();
            
            while(rs.next()) {                
                item.setIdOrden(Long.parseLong(rs.getString("idOrden")));
                item.setIdCliente(Long.parseLong(rs.getString("idCliente")));
                item.setIdCajero(Long.parseLong(rs.getString("idCajero")));
                item.setMontoTotal(Double.parseDouble(rs.getString("montoTotal")));
                item.setDescuento(Double.parseDouble(rs.getString("descuento")));
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
    
    public boolean insertar(Ordenes item){
        Connection con = getConnection();
        String query = "INSERT INTO Ordenes(idCliente,idCajero,"
                + "montoTotal,descuento,fecha,fechaHora,timestamp"
                + " VALUES(?,?,?,?,?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setLong(i++,item.getIdCliente());
            ps.setLong(i++,item.getIdCajero());
            ps.setDouble(i++,item.getMontoTotal());
            ps.setDouble(i++,item.getDescuento());
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
    
    public boolean actualizar(Ordenes item){
        Connection con = getConnection();
        String query = "UPDATE Ordenes SET idCliente = ?,idCajero = ?,"
                + "montoTotal = ?,descuento = ?,"
                + "fecha = ?,fechaHora = ?,timestamp = ?"
                + " WHERE idOrden = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setLong(i++,item.getIdCliente());
            ps.setLong(i++,item.getIdCajero());
            ps.setDouble(i++,item.getMontoTotal());
            ps.setDouble(i++,item.getDescuento());
            ps.setDate(i++,item.getFecha());
            ps.setDate(i++,item.getFechaHora());
            ps.setLong(i++,item.getTimestamp());
            ps.setLong(i++,item.getIdOrden());
            
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
        String query = "DELETE FROM Ordenes WHERE idDetalle = ?";
        
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
