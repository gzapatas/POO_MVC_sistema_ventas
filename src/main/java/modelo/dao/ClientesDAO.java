package modelo.dao;

import modelo.db.ConexionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.db.Clientes;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gzapata
 */
public class ClientesDAO extends ConexionFactory {
    public ArrayList<Clientes> listar(){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "SELECT idCliente,nombres,apellidos,telefono,celular,"
                + "tipoDocumento,documento,fechaNacimiento,fecha,fechaHora,timestamp"
                + " FROM Clientes";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Clientes> list = new ArrayList<Clientes>();
            
            while(rs.next()) {
                Clientes item = new Clientes();
                
                item.setIdCliente(Long.parseLong(rs.getString("idCliente")));
                item.setNombres(rs.getString("nombres"));
                item.setApellidos(rs.getString("apellidos"));
                item.setTelefono(rs.getString("telefono"));
                item.setCelular(rs.getString("celular"));
                item.setTipoDocumento(rs.getString("tipoDocumento"));
                item.setDocumento(rs.getString("documento"));
                item.setFechaNacimiento(rs.getString("fechaNacimiento"));
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
    
    public Clientes buscar(long id){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "SELECT idCliente,nombres,apellidos,telefono,celular,"
                + "tipoDocumento,documento,fechaNacimiento,fecha,fechaHora,timestamp"
                + " FROM Clientes WHERE idCliente = ? LIMIT 1";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            Clientes item = new Clientes();
            
            while(rs.next()) {                
                item.setIdCliente(Long.parseLong(rs.getString("idCliente")));
                item.setNombres(rs.getString("nombres"));
                item.setApellidos(rs.getString("apellidos"));
                item.setTelefono(rs.getString("telefono"));
                item.setCelular(rs.getString("celular"));
                item.setTipoDocumento(rs.getString("tipoDocumento"));
                item.setDocumento(rs.getString("documento"));
                item.setFechaNacimiento(rs.getString("fechaNacimiento"));
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
    
    public boolean sp_insertarcliente(Clientes item){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "CALL sp_insertarcliente(?,?,?,?,?,?,?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setString(i++,item.getNombres());
            ps.setString(i++,item.getApellidos());
            ps.setString(i++,item.getTelefono());
            ps.setString(i++,item.getCelular());
            ps.setString(i++,item.getDocumento());
            ps.setString(i++,item.getTipoDocumento());
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
    
    public boolean sp_editarcliente(Clientes item){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "CALL sp_editarcliente(?,?,?,?,?,?,?,?,?,?,?);";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            
            int i = 1;
            
            ps.setLong(i++,item.getIdCliente());
            ps.setString(i++,item.getNombres());
            ps.setString(i++,item.getApellidos());
            ps.setString(i++,item.getTelefono());
            ps.setString(i++,item.getCelular());
            ps.setString(i++,item.getDocumento());
            ps.setString(i++,item.getTipoDocumento());
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
    
    public boolean sp_eliminarcliente(long id){
        Connection con = getConnection(ConexionFactory.MYSQL);
        String query = "CALL sp_eliminarcliente(?);";
        
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
