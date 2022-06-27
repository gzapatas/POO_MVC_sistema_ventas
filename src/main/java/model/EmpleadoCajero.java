package model;

import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gzapata
 */
public class EmpleadoCajero extends Empleado implements EmpleadoInterface {
    @Override
    public String getTipoEmpleado() {
        return "Cajero";
    }
}
