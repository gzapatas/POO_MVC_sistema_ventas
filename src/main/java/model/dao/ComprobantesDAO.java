package model.dao;

import database.ConexionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.db.Comprobantes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gzapata
 */
public class ComprobantesDAO extends ConexionFactory {
    public ArrayList<Comprobantes> listar(){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "SELECT idComprobante,idOrden,tipo,codigo,subTotal,impuesto,"
                + "fecha,fechaHora,timestamp"
                + " FROM Comprobantes";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Comprobantes> list = new ArrayList<Comprobantes>();
            
            while(rs.next()) {
                Comprobantes item = new Comprobantes();
                
                item.setIdComprobante(Long.parseLong(rs.getString("idComprobante")));
                item.setIdOrden(Long.parseLong(rs.getString("idOrden")));
                item.setTipo(rs.getString("tipo"));
                item.setCodigo(rs.getString("codigo"));
                item.setSubTotal(Double.parseDouble(rs.getString("subTotal")));
                item.setImpuesto(Double.parseDouble(rs.getString("impuesto")));
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
    
    public Comprobantes buscar(long id){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "SELECT idComprobante,idOrden,tipo,codigo,subTotal,impuesto,"
                + "fecha,fechaHora,timestamp FROM Comprobantes"
                + " WHERE idComprobante = ? LIMIT 1";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            Comprobantes item = new Comprobantes();
            
            while(rs.next()) {                
                item.setIdComprobante(Long.parseLong(rs.getString("idComprobante")));
                item.setIdOrden(Long.parseLong(rs.getString("idOrden")));
                item.setTipo(rs.getString("tipo"));
                item.setCodigo(rs.getString("codigo"));
                item.setSubTotal(Double.parseDouble(rs.getString("subTotal")));
                item.setImpuesto(Double.parseDouble(rs.getString("impuesto")));
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
    
    public boolean insertar(Comprobantes item){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "INSERT INTO Comprobantes(idOrden,tipo,codigo,subTotal,"
                + "impuesto,fecha,fechaHora,timestamp)"
                + " VALUES(?,?,?,?,?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setLong(i++,item.getIdOrden());
            ps.setString(i++,item.getTipo());
            ps.setString(i++,item.getCodigo());
            ps.setDouble(i++,item.getSubTotal());
            ps.setDouble(i++,item.getImpuesto());
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
    
    public boolean actualizar(Comprobantes item){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "UPDATE Comprobantes SET idOrden = ?,tipo = ?,codigo = ?,"
                + "subTotal = ?,impuesto = ?,fecha = ?,fechaHora = ?,timestamp = ?"
                + " WHERE idComprobante = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setLong(i++,item.getIdOrden());
            ps.setString(i++,item.getTipo());
            ps.setString(i++,item.getCodigo());
            ps.setDouble(i++,item.getSubTotal());
            ps.setDouble(i++,item.getImpuesto());
            ps.setDate(i++,item.getFecha());
            ps.setDate(i++,item.getFechaHora());
            ps.setLong(i++,item.getTimestamp());
            ps.setLong(i++,item.getIdComprobante());
            
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
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "DELETE FROM Comprobantes WHERE idComprobante = ?";
        
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
