/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

/**
 *
 * @author gzapata
 */

import modelo.db.ConexionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import modelo.db.Empleados;

public class EmpleadosDAO extends ConexionFactory {
    public ArrayList<Empleados> listar(){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "SELECT apellidos,celular,documento,fecha,"
                + "fechaHora,fechaNacimiento,idEmpleado,nombres,tipoEmpleado,"
                + "telefono,timestamp FROM Empleados";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Empleados> list = new ArrayList<>();
            
            while(rs.next()) {
                Empleados item = new Empleados();
                
                item.setApellidos(rs.getString("apellidos"));
                item.setCelular(rs.getString("celular"));
                item.setDocumento(rs.getString("documento"));
                item.setFecha(rs.getString("fecha"));
                item.setFechaHora(rs.getString("fechaHora"));
                item.setFechaNacimiento(rs.getString("fechaNacimiento"));
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
                item.setFecha(rs.getString("fecha"));
                item.setFechaHora(rs.getString("fechaHora"));
                item.setFechaNacimiento(rs.getString("fechaNacimiento"));
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
    
    public boolean sp_insertarempleado(Empleados item){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "CALL sp_insertarempleado(?,?,?,?,?,?,?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setString(i++,item.getNombres());
            ps.setString(i++,item.getApellidos());
            ps.setString(i++,item.getTelefono());
            ps.setString(i++,item.getCelular());
            ps.setString(i++,item.getDocumento());
            ps.setString(i++,item.getTipoEmpleado());
            ps.setString(i++,item.getFechaNacimiento());
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
    
    public boolean sp_editarempleado(Empleados item){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "CALL sp_editarempleado(?,?,?,?,?,?,?,?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setLong(i++,item.getIdEmpleado());
            ps.setString(i++,item.getNombres());
            ps.setString(i++,item.getApellidos());
            ps.setString(i++,item.getTelefono());
            ps.setString(i++,item.getCelular());
            ps.setString(i++,item.getDocumento());
            ps.setString(i++,item.getTipoEmpleado());
            ps.setString(i++,item.getFechaNacimiento());
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
    
    public boolean sp_eliminarempleado(long id){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "CALL sp_eliminarempleado(?);";
        
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
