package database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gzapata
 */

import java.sql.Connection;

public interface ConexionInterface{
    public void initialize();
    public Connection getConnection();
}
