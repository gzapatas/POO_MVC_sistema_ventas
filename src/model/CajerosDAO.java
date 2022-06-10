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

public class CajerosDAO extends Conexion {
    public ArrayList<Cajeros> listar(){
        Connection con = getConnection();
        String query = "SELECT apellidos,celular,documento,fecha,"
                + "fechaHora,fechaNacimiento,idCajero,nombres,"
                + "telefono,timestamp FROM Cajeros";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Cajeros> list = new ArrayList<Cajeros>();
            
            while(rs.next()) {
                Cajeros item = new Cajeros();
                
                item.setApellidos(rs.getString("apellidos"));
                item.setCelular(rs.getString("celular"));
                item.setDocumento(rs.getString("documento"));
                item.setFecha(rs.getDate("fecha"));
                item.setFechaHora(rs.getDate("fechaHora"));
                item.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                item.setIdCajero(Long.parseLong(rs.getString("idCajero")));
                item.setNombres(rs.getString("nombres"));
                item.setTelefono(rs.getString("telefono"));
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
    
    public Cajeros buscar(long id){
        Connection con = getConnection();
        String query = "SELECT apellidos,celular,documento,fecha,"
                + "fechaHora,fechaNacimiento,idCajero,nombres,"
                + "telefono,timestamp FROM Cajeros WHERE idCajero = ? LIMIT 1";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            Cajeros item = new Cajeros();
            
            while(rs.next()) {                
                item.setApellidos(rs.getString("apellidos"));
                item.setCelular(rs.getString("celular"));
                item.setDocumento(rs.getString("documento"));
                item.setFecha(rs.getDate("fecha"));
                item.setFechaHora(rs.getDate("fechaHora"));
                item.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                item.setIdCajero(Long.parseLong(rs.getString("idCajero")));
                item.setNombres(rs.getString("nombres"));
                item.setTelefono(rs.getString("telefono"));
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
    
    public boolean insertar(Cajeros item){
        Connection con = getConnection();
        String query = "INSERT INTO Cajeros(apellidos,celular,documento,"
                + "fecha,fechaHora,fechaNacimiento,nombres,telefono,timestamp)"
                + " VALUES(?,?,?,?,?,?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setString(i++,item.getApellidos());
            ps.setString(i++,item.getCelular());
            ps.setString(i++,item.getDocumento());
            ps.setDate(i++,item.getFecha());
            ps.setDate(i++,item.getFechaHora());
            ps.setDate(i++,item.getFechaNacimiento());
            ps.setString(i++,item.getNombres());
            ps.setString(i++,item.getTelefono());
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
    
    public boolean actualizar(Cajeros item){
        Connection con = getConnection();
        String query = "UPDATE Cajeros SET apellidos = ?,celular = ?,"
                + "documento = ?,fecha = ?,fechaHora = ?,fechaNacimiento = ?,"
                + "nombres = ?,telefono = ?,timestamp = ?"
                + " WHERE idCajero = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setString(i++,item.getApellidos());
            ps.setString(i++,item.getCelular());
            ps.setString(i++,item.getDocumento());
            ps.setDate(i++,item.getFecha());
            ps.setDate(i++,item.getFechaHora());
            ps.setDate(i++,item.getFechaNacimiento());
            ps.setString(i++,item.getNombres());
            ps.setString(i++,item.getTelefono());
            ps.setLong(i++,item.getTimestamp());
            ps.setLong(i++,item.getIdCajero());
            
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
        String query = "DELETE FROM Cajeros WHERE idCajero = ?";
        
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
