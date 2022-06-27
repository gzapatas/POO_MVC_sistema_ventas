/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

/**
 *
 * @author gzapata
 */

import database.ConexionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import model.db.Empleados;

public class EmpleadosDAO extends ConexionFactory {
    public ArrayList<Empleados> listar(){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "SELECT apellidos,celular,documento,fecha,"
                + "fechaHora,fechaNacimiento,idEmpleado,nombres,tipoEmpleado,"
                + "telefono,timestamp, FROM Empleados";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Empleados> list = new ArrayList<Empleados>();
            
            while(rs.next()) {
                Empleados item = new Empleados();
                
                item.setApellidos(rs.getString("apellidos"));
                item.setCelular(rs.getString("celular"));
                item.setDocumento(rs.getString("documento"));
                item.setFecha(rs.getDate("fecha"));
                item.setFechaHora(rs.getDate("fechaHora"));
                item.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                item.setIdEmpleado(Long.parseLong(rs.getString("idEmpleado")));
                item.setNombres(rs.getString("nombres"));
                item.setTipoEmpleado(rs.getString("tipoEmpleado"));
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
    
    public Empleados buscar(long id){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "SELECT apellidos,celular,documento,fecha,"
                + "fechaHora,fechaNacimiento,idEmpleado,nombres,tipoEmpleado,"
                + "telefono,timestamp FROM Empleados WHERE idEmpleado = ? LIMIT 1";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            Empleados item = new Empleados();
            
            while(rs.next()) {                
                item.setApellidos(rs.getString("apellidos"));
                item.setCelular(rs.getString("celular"));
                item.setDocumento(rs.getString("documento"));
                item.setFecha(rs.getDate("fecha"));
                item.setFechaHora(rs.getDate("fechaHora"));
                item.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                item.setIdEmpleado(Long.parseLong(rs.getString("idEmpleado")));
                item.setNombres(rs.getString("nombres"));
                item.setTipoEmpleado(rs.getString("tipoEmpleado"));
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
    
    public boolean insertar(Empleados item){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "INSERT INTO Empleados(apellidos,celular,documento,tipoEmpleado,"
                + "fecha,fechaHora,fechaNacimiento,nombres,telefono,timestamp)"
                + " VALUES(?,?,?,?,?,?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setString(i++,item.getApellidos());
            ps.setString(i++,item.getCelular());
            ps.setString(i++,item.getDocumento());
            ps.setString(i++,item.getTipoEmpleado());
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
    
    public boolean actualizar(Empleados item){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "UPDATE Empleados SET apellidos = ?,celular = ?,tipoEmpleado = ?"
                + "documento = ?,fecha = ?,fechaHora = ?,fechaNacimiento = ?,"
                + "nombres = ?,telefono = ?,timestamp = ?"
                + " WHERE idEmpleado = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setString(i++,item.getApellidos());
            ps.setString(i++,item.getCelular());
            ps.setString(i++,item.getTipoEmpleado());
            ps.setString(i++,item.getDocumento());
            ps.setDate(i++,item.getFecha());
            ps.setDate(i++,item.getFechaHora());
            ps.setDate(i++,item.getFechaNacimiento());
            ps.setString(i++,item.getNombres());
            ps.setString(i++,item.getTelefono());
            ps.setLong(i++,item.getTimestamp());
            ps.setLong(i++,item.getIdEmpleado());
            
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
        String query = "DELETE FROM Empleados WHERE idEmpleado = ?";
        
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
